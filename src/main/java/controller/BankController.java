package controller;

import businessLayer.Bank;
import dataAccessLayer.BankHashMapDAO;
import entities.Account;
import entities.Person;
import presentation.BankView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by radu on 12.05.2017.
 */
public class BankController {

    int currentPersonId = -1;
    private String bankFilePath = "ser/bank.ser";

    private BankView view;
    private Bank bankService;

    public BankController(BankView view) {
        this.view = view;
        this.bankService = new Bank(new BankHashMapDAO(bankFilePath));

        view.addOtherButtonsListener(new OtherButtonsActionListener());
        view.addPersonButtonsListener(new PersonButtonsActionListener());
        view.addPersonTableSelectionListener(new PersonTableSelectionListener());
        view.addAccountButtonsListener(new AccountButtonsActionListener());
        view.addAccountTableSelectionListener(new AccountTableSelectionListener());
    }


    class OtherButtonsActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            bankService.reinitializeBankFile(bankFilePath);
        }
    }

    class PersonButtonsActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "read":
                    view.updatePersonTable(bankService.getMappedAllPerson());
                    break;
                case "add":
                    bankService.addNewPerson(new Person(
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
                    if(Integer.parseInt(view.getAccountPersonIdInput()) == currentPersonId){
                        bankService.addNewAccount(new Account(
                                Integer.parseInt(view.getAccountIdInput()),
                                view.getAccountNumberInput(),
                                view.getAccountTypeInput(),
                                Double.parseDouble(view.getAccountBalanceInput()),
                                Integer.parseInt(view.getAccountPersonIdInput())));
                    }
                    else {
                        //display error message
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
        }
    }
}
