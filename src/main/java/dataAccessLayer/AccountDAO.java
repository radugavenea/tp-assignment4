package dataAccessLayer;

import entities.Account;

import java.util.List;

/**
 * Created by radu on 14.05.2017.
 */
public class AccountDAO {

    private final String accountFileName = "ser/account.ser";

    /**
     *  Retrieves all the accounts stored in the account file
     * @return  the List of accounts if there are any, null otherwise
     */
    public List<Account> getAllAccounts(){
        return (List<Account>) SerializationHelper.readFromFile(accountFileName);
    }
}
