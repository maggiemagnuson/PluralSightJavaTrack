package com.maggie.fundamentals;

/**
 * Created by z001hqv on 2/19/17.
 */
public class Account {
    private final String id;
    private double balance = 0d;

    public Account(String id){
        this.id = id;
    }

    public Account(String id, double balance){
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount){
        this.balance += amount;
    }

    public synchronized  void withdrawal(double amount){
        this.balance -= amount;
    }
}
