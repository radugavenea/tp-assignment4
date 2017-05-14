package businessLayer;

import dataAccessLayer.AccountDAO;
import dataAccessLayer.PersonDAO;
import dataAccessLayer.SerializationHelper;
import entities.Account;
import entities.Person;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by radu on 12.05.2017.
 */
public class Bank implements BankProc {

    private PersonDAO personDAO;
    private AccountDAO accountDAO;
    private Set<Person> personSet;
    private List<Account> accountList;

    public Bank(PersonDAO personDAO, AccountDAO accountDAO) {
        this.personDAO = personDAO;
        this.accountDAO = accountDAO;

        this.personSet = personDAO.getAllPerson();
        this.accountList = accountDAO.getAllAccounts();
    }

    public void reinitializeFiles() {
        SerializationHelper.initializeFiles();
    }

    public Set<Person> getAllPerson() {
        return personDAO.getAllPerson();
    }

    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    public List<Account> getAllAccountsByPersonId(int id) {
        try{
            return accountList.stream()
                    .filter(p -> p.getPersonId() == id)
                    .collect(Collectors.toList());
        }
        catch (NullPointerException e){
            return null;
        }
    }

    public List<Person> getMappedAllPerson() {
        try{
            return personSet.stream()
                .collect(Collectors.toList());
        }
        catch (NullPointerException e){
            return null;
        }
    }

    public int addNewPerson(Person person) {
        personSet.add(person);
        return person.getId();
    }

    public int deletePerson(Person person) {
        int personId = person.getId();
        personSet.remove(person);
        return personId;
    }

    public int deletePersonById(int id) {
        try{
            personSet.remove(personSet.stream()
                .filter(p -> p.getId() == id)
                .collect(Collectors.toList()).get(0));
        }
        catch (IndexOutOfBoundsException e){
            return -1;
        }
        return id;
    }

}
