package com.maggie.fundamentals.serializable;

import org.apache.log4j.Logger;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by z001hqv on 3/20/17.
 */
public class BankAccount implements Serializable {
    private static final Logger LOG = Logger.getLogger(BankAccount.class);
    private static final long serialVersionUID = -849824544876872495L;

    private String id;
    private float balance;
    private char lastTxType;
    private float lastTxAmt;
    private boolean isVip = false;

    public BankAccount(String id, float balance) {
        this.id = id;
        this.balance = balance;
    }

    public synchronized void deposit(float amount) {
        if (amount > 9999.99f) {
            LOG.warn("Trigger check on deposit");
        }
        setLastTransaction('D', amount);
        balance += amount;
    }

    public synchronized void withdrawal(float amount) {
        if (amount > balance) {
            setLastTransaction('O', amount);
            throw new RuntimeException("Insufficient funds for withdrawal");
        }
        setLastTransaction('W', amount);
        balance -= amount;
    }

    private void setLastTransaction(char lastTxType, float lastTxAmt) {
        this.lastTxType = lastTxType;
        this.lastTxAmt = lastTxAmt;
    }

    public String getId() {
        return id;
    }

    public float getBalance() {
        return balance;
    }

    public char getLastTxType() {
        return lastTxType;
    }

    public float getLastTxAmt() {
        return lastTxAmt;
    }

    public boolean isVip() {
        return isVip;
    }

    public void saveAccount(BankAccount ba, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            outputStream.writeObject(ba);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BankAccount readAccount(String filename) {
        BankAccount ba = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            ba = (BankAccount) inputStream.readObject();
            LOG.info(ba.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ba;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                ", lastTxType=" + lastTxType +
                ", lastTxAmt=" + lastTxAmt +
                ", isVip=" + isVip +
                '}';
    }
}
