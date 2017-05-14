package entities;

/**
 * Created by radu on 12.05.2017.
 */
public class SavingAccount extends Account {

    public SavingAccount(int id, String accountNumber, String type, double balance, int personId) {
        super(id, accountNumber, type, balance, personId);
    }
}
