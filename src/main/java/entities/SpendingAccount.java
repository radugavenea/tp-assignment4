package entities;

/**
 * Created by radu on 12.05.2017.
 */
public class SpendingAccount extends Account {
    public SpendingAccount(int id, String accountNumber, String type, double balance, int personId) {
        super(id, accountNumber, type, balance, personId);
    }
}
