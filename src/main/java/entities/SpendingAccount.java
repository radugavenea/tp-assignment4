package entities;

/**
 * Created by radu on 12.05.2017.
 */
public class SpendingAccount extends Account {

    private double limit;

    public SpendingAccount(int id, String accountNumber, String type, double balance, int personId, double limit) {
        super(id, accountNumber, type, balance, personId);
        this.limit = limit;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
