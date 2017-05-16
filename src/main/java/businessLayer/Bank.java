package businessLayer;

import dataAccessLayer.BankHashMapDAO;
import dataAccessLayer.SerializationHelper;
import entities.Account;
import entities.Person;
import entities.SavingAccount;
import entities.SpendingAccount;

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
        bankHashMap.entrySet().removeIf(entry -> entry.getKey().getId() == id);
        return id;
    }

    @Override
    public List<Account> getAccountsByPersonId(int id) {
        return bankHashMap.get(getPersonById(id));
    }


    @Override
    public int addNewAccount(Account account) {
        List<Account> accountList = bankHashMap.get(getPersonById(account.getPersonId()));
        accountList.add(account);
        return account.getId();
    }

    @Override
    public int deleteAccountById(int id) {
        for(List<Account> accountList : bankHashMap.values()){
            for(Iterator<Account> iterator = accountList.iterator(); iterator.hasNext();){
                if(iterator.next().getId() == id){
                    iterator.remove();
                }
            }
        }
        return id;
    }

    /**
     *  Method used to deposit money into an Account
     * @param sum
     * @param accountId
     * @return  0 if transaction is successful
     * @return -3 for Saving Account if its not the first deposit and transaction is not allowed
     */
    @Override
    public int addMoneyToAccount(double sum, int accountId) {
        Account account = getAccountById(accountId);

        if(account instanceof SavingAccount){
            return addToSavingAccount((SavingAccount) account,sum);
        }
        else if(account instanceof SpendingAccount) {
            return addToSpendingAccount((SpendingAccount) account,sum);
        }
        return 0;
    }

    /**
     *  Method used to withdraw money from an Account
     * @param sum
     * @param accountId
     * @return 0 if transaction is completed with success
     * @return -1 for Spending Account if the sum to be withdraw is larger than the account limit
     * @return -2 if there are not enough money in the account for the requested sum
     * @return -3 for Saving Account if its not the first withdraw and transaction is not allowed
     */
    @Override
    public int withdrawMoneyFromAccount(double sum, int accountId) {
        Account account = getAccountById(accountId);

        if(account instanceof SavingAccount){
            return withdrawFromSavingAccount((SavingAccount)account,sum);
        }
        else if(account instanceof SpendingAccount){
            if(((SpendingAccount) account).getLimit() < sum){
                return -1;
            }
            return withdrawFromSpendingAccount((SpendingAccount)account,sum);
        }
        return 0;
    }

    @Override
    public Person getPersonById(int id){
        try{
            return bankHashMap.keySet().stream()
                    .filter(e -> e.getId() == id)
                    .collect(Collectors.toList()).get(0);
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    @Override
    public Account getAccountById(int id){
        Account account = null;
        for(List<Account> accountList : bankHashMap.values()){
            for(Iterator<Account> iterator = accountList.iterator(); iterator.hasNext(); ){
                Account tempAccount = iterator.next();
                if(tempAccount.getId() == id){
                    account = tempAccount;
                }
            }
        }
        return account;
    }





    private int addToSavingAccount(SavingAccount account, double sum){
        if(account.getIsFirstDeposit()){
            double newBalance = account.getBalance() + sum - getInterestForSavingAccountDeposit(account,sum);
            account.setBalance(newBalance);
            account.setIsFirstDeposit(false);
            return 0;
        }
        else return -3;
    }

    private int addToSpendingAccount(SpendingAccount account, double sum){
        double newBalance = account.getBalance() + sum;
        account.setBalance(newBalance);
        return 0;
    }

    private int withdrawFromSavingAccount(SavingAccount account, double sum){
        if(account.getIsFirstWithdraw()){
            if(account.getBalance() >= sum){
                double balance = account.getBalance() - sum;
                account.setBalance(balance);
                account.setIsFirstWithdraw(false);
                return 0;
            }
            else return -2;
        }
        else return -3;
    }

    private int withdrawFromSpendingAccount(SpendingAccount account, double sum){
        if(account.getBalance() >= sum){
            double balance = account.getBalance() - sum;
            account.setBalance(balance);
            return 0;
        }
        else {
            return -2;
        }
    }

    public double getInterestForSavingAccountDeposit(SavingAccount account,double sum){
        return account.getDate() / 30.0 * (account.getInterest() / 100) * sum;
    }
}
