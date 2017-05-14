package businessLayer;

import dataAccessLayer.BankHashMapDAO;
import dataAccessLayer.SerializationHelper;
import entities.Account;
import entities.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by radu on 12.05.2017.
 */
public class Bank implements BankProc {

    private BankHashMapDAO bankHashMapDAO;
    private Map<Person, List<Account>> bankHashMap;

    public Bank(BankHashMapDAO bankHashMapDAO) {
        this.bankHashMapDAO = bankHashMapDAO;

        this.bankHashMap = bankHashMapDAO.getBankHashMap();
    }

    @Override
    public void reinitializeBankFile(String bankFilePath){
        SerializationHelper.initializeBankFile(bankFilePath);
    }

    @Override
    public Set<Person> getAllPerson() {
        Set<Person> personSet = bankHashMap.keySet();
        return personSet;
    }

    @Override
    public List<Person> getMappedAllPerson() {
        try{
            return getAllPerson().stream()
                .collect(Collectors.toList());
        }
        catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public int addNewPerson(Person person) {
        bankHashMap.put(person,new LinkedList<>());
        return person.getId();
    }

    @Override
    public int deletePerson(Person person) {
        int personId = person.getId();
        bankHashMap.remove(person);
        return personId;
    }

    @Override
    public int deletePersonById(int id) {
        bankHashMap.remove(getPersonById(id));
        return id;
    }

    @Override
    public List<Account> getAccountsByPersonId(int id) {
        return bankHashMap.get(getPersonById(id));
    }

    @Override
    public int addNewAccount(Account account) {
        Person person = getPersonById(account.getPersonId());
        List<Account> accountList = bankHashMap.get(person);
        accountList.add(account);
        bankHashMap.put(person,accountList);
        return account.getId();
    }

    @Override
    public int deleteAccountById(int id) {
        for(Person p : bankHashMap.keySet()){
            List<Account> accountList = bankHashMap.get(p);
            for(Account a : accountList){
                if(a.getId() == id){
                    accountList.remove(a);
                }
            }
            bankHashMap.put(p,accountList);
        }
        return id;
    }


    private Person getPersonById(int id){
        try{
            return bankHashMap.keySet().stream()
                    .filter(p -> p.getId() == id)
                    .collect(Collectors.toList()).get(0);
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
    }




}
