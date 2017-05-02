package com.maggie.fundamentals.serializable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.swing.BakedArrayList;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by z001hqv on 3/30/17.
 */
public class AccountGroupTest {

    @Test
    public void testSerialization(){
        BankAccount ba1 = new BankAccount("afdasdf123123", 0.00f);
        ba1.deposit(25f);
        BankAccount ba2 = new BankAccount("314lkjdafakj12", 500f);
        ba2.withdrawal(100f);
        BankAccount ba3 = new BankAccount("afdasdf123123", 1000f);
        ba3.deposit(1000f);
        BankAccount ba4 = new BankAccount("314lkjdafakj12", 100f);
        ba4.withdrawal(100f);

        AccountGroup ag = new AccountGroup();
        ag.addAccount(ba1);
        ag.addAccount(ba2);
        ag.addAccount(ba3);
        ag.addAccount(ba4);

        ag.saveAccountGroup("/Users/z001hqv/GitProjects/PluralSightJavaTrack/data/account-group-v1.txt");

        AccountGroup accounts = ag.readAccount("/Users/z001hqv/GitProjects/PluralSightJavaTrack/data/account-group-v1.txt");

        assertEquals(0, accounts.getTotalBalance());
    }

}
