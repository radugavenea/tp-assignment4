package dataAccessLayer;

import entities.Account;
import entities.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by radu on 14.05.2017.
 */
public class BankHashMapDAO {

    private String bankFilePath;

    public BankHashMapDAO(String bankFilePath) {
        this.bankFilePath = bankFilePath;
    }

    public Map<Person, List<Account>> getBankHashMap(){
        Map<Person, List<Account>> bankHashMap = (Map<Person, List<Account>>) SerializationHelper.readFromFile(bankFilePath);
        return bankHashMap != null ? bankHashMap : new HashMap<>();
    }
}
