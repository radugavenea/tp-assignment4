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
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (getId() != account.getId()) return false;
        if (Double.compare(account.getBalance(), getBalance()) != 0) return false;
        if (getPersonId() != account.getPersonId()) return false;
        if (!getAccountNumber().equals(account.getAccountNumber())) return false;
        return getType().equals(account.getType());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + getAccountNumber().hashCode();
        result = 31 * result + getType().hashCode();
        temp = Double.doubleToLongBits(getBalance());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getPersonId();
        return result;
    }
}
