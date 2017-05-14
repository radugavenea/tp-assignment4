package entities;

import java.util.List;
import java.util.Map;

/**
 * Created by radu on 14.05.2017.
 */
public class BankAccountsHash {


    private Map<Person, List<Account>> bankHash;

    public BankAccountsHash(Map<Person, List<Account>> bankHash) {
        this.bankHash = bankHash;
    }


    public Map<Person, List<Account>> getBankHash() {
        return bankHash;
    }

    public void setBankHash(Map<Person, List<Account>> bankHash) {
        this.bankHash = bankHash;
    }


}
