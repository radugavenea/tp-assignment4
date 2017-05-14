package entities;

import java.io.Serializable;

/**
 * Created by radu on 12.05.2017.
 */
public class Account implements Serializable {

    private int id;
    private String accountNumber;
    private String type;
    private double balance;
    private int personId;

    public Account(int id, String accountNumber, String type, double balance, int personId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.type = type;
        this.balance = balance;
        this.personId = personId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != account.id) return false;
        if (Double.compare(account.balance, balance) != 0) return false;
        if (personId != account.personId) return false;
        if (accountNumber != null ? !accountNumber.equals(account.accountNumber) : account.accountNumber != null)
            return false;
        return type != null ? type.equals(account.type) : account.type == null;
    }


    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + personId;
        return result;
    }
}
