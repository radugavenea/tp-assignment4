package businessLayer;

import entities.Account;
import entities.Person;
import entities.SavingAccount;

import java.util.List;
import java.util.Set;

/**
 * Created by radu on 12.05.2017.
 */
public interface BankProc {

    void reinitializeBankFile(String bankFilePath);
    void saveIntoBankFile(String bankFilePath);
    Set<Person> getAllPerson();
    List<Person> getMappedAllPerson();
    int addNewPerson(Person person);
    int editPerson(Person person);
    int deletePerson(Person person);
    int deletePersonById(int id);
    List<Account> getAccountsByPersonId(int id);
    int addNewAccount(Account account);
    int deleteAccountById(int id);
    int addMoneyToAccount(double sum, int accountId);
    int withdrawMoneyFromAccount(double sum, int accountId);
    Person getPersonById(int id);
    Account getAccountById(int id);
    double getInterestForSavingAccountDeposit(SavingAccount account, double sum);
}
