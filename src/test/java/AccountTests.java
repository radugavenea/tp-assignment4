import businessLayer.Bank;
import businessLayer.BankProc;
import dataAccessLayer.BankHashMapDAO;
import dataAccessLayer.SerializationHelper;
import entities.Account;
import entities.SavingAccount;
import entities.SpendingAccount;
import javafx.scene.shape.SVGPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by radu on 14.05.2017.
 */
public class AccountTests {

    private String bankFileForTest = "ser/test/bank.ser";
    private BankProc bankService;

    @Before
    public void init(){
        bankService = new Bank(new BankHashMapDAO(bankFileForTest));
        SerializationHelper.initializeBankFile(bankFileForTest);
    }

    @Test
    public void getAllAccountByPersonId(){
        int count = bankService.getAccountsByPersonId(1).size();
        assert count == 2;
    }

    @Test
    public void addNewAccountTest(){
        assert bankService.getAccountsByPersonId(1).size() == 2;
        bankService.addNewAccount(new Account(100,"3423423","spending", 100,1));
        assert bankService.getAccountsByPersonId(1).size() == 3;
    }

    @Test
    public void deleteAccountTest(){
        List<Account> accountList = bankService.getAccountsByPersonId(1);
        int count = accountList.size();
        bankService.deleteAccountById(accountList.get(0).getId());
        assert bankService.getAccountsByPersonId(1).size() == count - 1;
    }

    @Test
    public void depositMoneyIntoAccountTest(){
        List<Account> accounts = bankService.getAccountsByPersonId(4);
        Account account = accounts.get((int) Math.random() * accounts.size());
        double initialSum = account.getBalance();
        bankService.addMoneyToAccount(1239,account.getId());
        if(account instanceof SavingAccount){
            assert account.getBalance() != initialSum + 1239 * ((SavingAccount) account).getInterest()
                    / 100 * ((SavingAccount) account).getDate() / 30;
        }
        else if(account instanceof SpendingAccount){
            assert account.getBalance() == initialSum + 1239;
        }
    }

    @After
    public void tearDown(){}

}
