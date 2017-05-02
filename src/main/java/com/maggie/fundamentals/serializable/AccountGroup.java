package com.maggie.fundamentals.serializable;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by z001hqv on 3/30/17.
 */
public class AccountGroup implements Serializable {
    private static final Logger LOG = Logger.getLogger(AccountGroup.class);
    private static final long serialVersionUID = 8066751683872005598L;
    private Map<String, BankAccount> accountMap = new HashMap<>();

    private transient int totalBalance;

    public int getTotalBalance() {
        return totalBalance;
    }

    public Map<String, BankAccount> getAccountMap() {
        return accountMap;
    }

    public void addAccount(BankAccount account){
        totalBalance += account.getBalance();
        accountMap.put(account.getId(), account);
    }

    public void saveAccountGroup(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            outputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AccountGroup readAccount(String filename) {
        AccountGroup ba = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            ba = (AccountGroup) inputStream.readObject();
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
        return "AccountGroup{" +
                "accountMap=" + accountMap +
                ", totalBalance=" + totalBalance +
                '}';
    }
}
