package businessLayer;

import entities.Account;
import entities.Person;

import java.util.List;
import java.util.Set;

/**
 * Created by radu on 12.05.2017.
 */
public interface BankProc {

    void reinitializeFiles();
    Set<Person> getAllPerson();
    List<Account> getAllAccounts();
    List<Account> getAllAccountsByPersonId(int id);
    List<Person> getMappedAllPerson();
    int addNewPerson(Person person);
    int deletePerson(Person person);
    int deletePersonById(int id);
}
