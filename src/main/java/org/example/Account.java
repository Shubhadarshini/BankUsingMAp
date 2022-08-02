package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

abstract class Account {

    private String accountNum;
    private double balance;
    private Date timestamp;
    private float interestRate;

    private float year;

    private String type;

    public Account(double balance, float interestRate, String cusId, String type) {
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountNum = ExRef.getInstance().getNewAccountNUm();
        this.type = "Savings";
        this.timestamp = new Date();

        ExRef.getInstance().addAccountToCustomer(cusId, this.accountNum);
        ExRef.getInstance().addCustomerToAccount(this.accountNum, cusId);

        AccountDetails.getInstance().addAccountTolist(this);

        System.out.println("Savings account created with Account Number : " + this.accountNum);

    }

    public Account(double balance, float interestRate, String cusId, float year, String type) {
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountNum = ExRef.getInstance().getNewAccountNUm();
        this.year = year;
        this.type = "Term deposit";
        this.timestamp = new Date();

        ExRef.getInstance().addAccountToCustomer(cusId, this.accountNum);
        ExRef.getInstance().addCustomerToAccount(this.accountNum, cusId);

        AccountDetails.getInstance().addAccountTolist(this);

        System.out.printf("Account created for customerId %s with Account Number %s\n", cusId, accountNum);

    }

    public String getAccountNum() {
        return accountNum;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNum='" + accountNum + '\'' +
                ", balance=" + balance +
                ", timestamp=" + timestamp +
                ", interestRate=" + interestRate +
                ", year=" + year +
                ", type='" + type + '\'' +
                '}';
    }

    public void addBalance(double amount) {
        this.balance += amount;
        System.out.printf("Deposited %.02f in account : %s \t", amount, this.accountNum);
        System.out.printf("Total balance : %.02f\n", this.balance);
    }

    public double getBalance() {
        return balance;
    }

    public void subtractBalance(double amount) {
        this.balance -= amount;
        System.out.printf("Amount of %.02f withdrawn from Account Number : %s \t", amount, this.accountNum);
        System.out.printf("Total balance : %.02f\n", this.balance);

    }

    public void addInterest() {

        long date = new Date().getTime()- timestamp.getTime();
        long diffdate = TimeUnit.MILLISECONDS.toSeconds(date);
        System.out.println(diffdate);
        if (diffdate > 30) {
            this.balance += (interestRate * balance);
            System.out.printf("updated balance of %s is %.02f\n", accountNum, balance);
        }
        else System.out.println("Not yet completed a year");
    }
}
