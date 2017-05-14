package businessLayer;

import entities.Account;
import entities.Person;

import java.util.List;
import java.util.Set;

/**
 * Created by radu on 12.05.2017.
 */
public interface BankProc {

    void reinitializeBankFile(String bankFilePath);
    Set<Person> getAllPerson();
    List<Person> getMappedAllPerson();
    int addNewPerson(Person person);
    int deletePerson(Person person);
    int deletePersonById(int id);
    List<Account> getAccountsByPersonId(int id);
    int addNewAccount(Account account);
    int deleteAccountById(int id);
}
