package com.maggie.fundamentals;

/**
 * Created by z001hqv on 2/17/17.
 */
public class BankAccount {
    private double balance;

    public BankAccount(double startBalance){
        balance = startBalance;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public double getBalanceNonThreadSafe(){
        return balance;
    }

    public void depositNonThreadSafe(double amount){
        balance += amount;
    }

    public synchronized void deposit(double amount){
        balance += amount;
    }

    public synchronized void withdrawal(double amount){
        balance -= amount;
    }
}
