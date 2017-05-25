package controller;

import businessLayer.AccountValidation;
import businessLayer.Bank;
import businessLayer.BankProc;
import dataAccessLayer.BankHashMapDAO;
import entities.Account;
import entities.Person;
import entities.SavingAccount;
import entities.SpendingAccount;
import presentation.BankView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by radu on 12.05.2017.
 */
public class BankController implements Observer {

    int currentPersonId = -1;
    private String bankFilePath = "ser/bank.ser";

    private AccountValidation accountValidation;
    private BankView view;
    private BankProc bankService;

    public BankController(BankView view) {
        this.view = view;
        this.bankService = new Bank(new BankHashMapDAO(bankFilePath));
        this.accountValidation = new AccountValidation();

        bankService.addObserver(this);
        view.addOtherButtonsListener(new OtherButtonsActionListener());
        view.addPersonButtonsListener(new PersonButtonsActionListener());
        view.addPersonTableSelectionListener(new PersonTableSelectionListener());
        view.addAccountButtonsListener(new AccountButtonsActionListener());
        view.addAccountTableSelectionListener(new AccountTableSelectionListener());
        view.addInitializeButtonListener(new InitializeButtonListener());
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String){
            view.displayAccountModificationToPerson((String) arg);
        }
        if(arg instanceof Account){
            view.updateAccountTable(bankService.getAccountsByPersonId(currentPersonId));
        }
        else if(arg instanceof Person){
            view.updatePersonTable(bankService.getMappedAllPerson());
        }
    }


    class PersonButtonsActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "read":
                    view.updatePersonTable(bankService.getMappedAllPerson());
                    break;
                case "add":
                    Person person = new Person(
                            bankService.getPersonLastId() + 1,
                            view.getPersonPNCInput(),
                            view.getPersonNameInput(),
                            view.getPersonAddressInput());
                    bankService.addNewPerson(person);
                    break;
                case "edit":
                    bankService.editPerson(new Person(
                            Integer.parseInt(view.getPersonIdInput()),
                            view.getPersonPNCInput(),
                            view.getPersonNameInput(),
                            view.getPersonAddressInput()));
                    break;
                case "delete":
                    bankService.deletePersonById(Integer.parseInt(view.getPersonIdInput()));
                    break;
            }
        }
    }

    class PersonTableSelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(view.getSelectedPersonId() != null){
                currentPersonId = Integer.parseInt(view.getSelectedPersonId());
                view.updatePersonInputValues();
                view.updateAccountTable(bankService.getAccountsByPersonId(currentPersonId));
            }
        }
    }

    class AccountButtonsActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "read":
                    if(currentPersonId > -1){
                        view.updateAccountTable(bankService.getAccountsByPersonId(currentPersonId));
                    }
                    break;
                case "add":
                    Account account = new Account(
                            bankService.getAccountLastId() + 1,
                            view.getAccountNumberInput(),
                            view.getAccountTypeInput(),
                            Double.parseDouble(view.getAccountBalanceInput()),
                            Integer.parseInt(view.getAccountPersonIdInput()));
                    if(accountValidation.isValid(account) &&
                            Integer.parseInt(view.getAccountPersonIdInput()) == currentPersonId) {
                        bankService.addNewAccount(account);
                    }
                    else{
                        view.displayAccountNotValidMessage();
                    }
                    break;
                case "delete":
                    bankService.deleteAccountById(Integer.parseInt(view.getAccountIdInput()));
                    break;
            }
        }
    }

    class AccountTableSelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            view.updateAccountInputs();
            view.updateOtherPanel();
        }
    }

    class OtherButtonsActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            Account account = bankService.getAccountById(Integer.parseInt(view.getAccountIdInput()));
            int returnedValue;
            switch (e.getActionCommand()){
                case "save":
                    bankService.saveIntoBankFile(bankFilePath);
                    break;
                case "addMoney":
                    if(account instanceof SavingAccount && !((SavingAccount) account).getIsFirstDeposit()){
                        view.displayOnlyOneDepositMessage();
                    }
                    returnedValue = bankService.addMoneyToAccount(Double.parseDouble(view.getAccountSumInput()),
                            Integer.parseInt(view.getAccountIdInput()));

                    displayCorrespondingDepositMessage(returnedValue,account);
                    break;
                case "withdraw":
                    if(account instanceof SavingAccount && !((SavingAccount) account).getIsFirstWithdraw()){
                        view.displayOnlyOneWithdrawMessage();
                    }
                    returnedValue = bankService.withdrawMoneyFromAccount(Double.parseDouble(view.getAccountSumInput()),
                            Integer.parseInt(view.getAccountIdInput()));

                    displayCorrespondingWithdrawMessage(returnedValue, account);
                    break;
            }
        }
    }

    class InitializeButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            bankService.reinitializeBankFile(bankFilePath);
        }
    }


    private void displayCorrespondingDepositMessage(int value, Account account){
        if(value == 0){
            if(account instanceof SpendingAccount){
                view.displayTransactionSuccessfulMessage();
            }
            else if(account instanceof SavingAccount){
                view.displaySavingAccountSuccessfulMessage(bankService.getInterestForSavingAccountDeposit(
                        (SavingAccount) account,Double.parseDouble(view.getAccountSumInput())
                ));
            }
        }
    }

    private void displayCorrespondingWithdrawMessage(int value, Account account){
        if(value == 0){
            view.displayTransactionSuccessfulMessage();
        }
        else if(value == -1 && account instanceof SpendingAccount){
            view.displayWithdrawLimitMessage(Double.toString(((SpendingAccount) account).getLimit()));
        }
        else if(value == -2){
            view.displayNotEnoughMoneyMessage();
        }
    }
}
