package entities;


import java.util.Date;

/**
 * Created by radu on 12.05.2017.
 */
public class SavingAccount extends Account {

    private int date;
    private double interest;
    private boolean isFirstDeposit;
    private boolean isFirstWithdraw;

    public SavingAccount(int id, String accountNumber, String type, double balance, int personId, int date, double interest) {
        super(id, accountNumber, type, balance, personId);
        this.date = date;
        this.interest = interest;
        this.isFirstDeposit = true;
        this.isFirstWithdraw = true;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public boolean getIsFirstDeposit(){
        return isFirstDeposit;
    }

    public void setIsFirstDeposit(boolean boo){
        isFirstDeposit = boo;
    }

    public boolean getIsFirstWithdraw(){
        return isFirstWithdraw;
    }

    public void setIsFirstWithdraw(boolean boo){
        isFirstWithdraw = boo;
    }
}
