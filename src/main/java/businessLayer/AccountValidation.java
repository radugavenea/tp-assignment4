package businessLayer;

import entities.Account;

/**
 * Created by radu on 25.05.2017.
 */
public class AccountValidation {

    public boolean isValid(Account account){
        if(! (account.getType().equals("saving") || account.getType().equals("spending")))
            return false;
        return true;
    }
}
