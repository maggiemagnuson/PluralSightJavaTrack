package com.maggie.fundamentals;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;


/**
 * Created by z001hqv on 2/19/17.
 */
public class AccountTest {

    private static final double DELTA = 1e-15;
    protected Account account;

    @Before
    public void setUp(){
        account = new Account("12345abcd", 500d);
    }

    @Test
    public void testId(){
        assertEquals("12345abcd", account.getId());
    }

    @Test
    public void testBalance(){
        assertEquals(500, account.getBalance(), DELTA);
    }

    @Test
    public void testDeposit(){
        Account account = new Account("abcdefg", 500d);
        account.deposit(100d);
        assertEquals(600d, account.getBalance(), DELTA);
    }

    @Test
    public void testWithdrawal(){
        Account account = new Account("zasdfwe23", 500d);
        account.withdrawal(100d);
        assertEquals(400d, account.getBalance(), DELTA);
    }

    @Test
    public void testIdOnlyConstructor(){
        Account account = new Account("abcd1345lj");
        assertEquals("abcd1345lj", account.getId());
    }

    @Test
    public void testIdOnlyConstructorBalance(){
        Account account = new Account("abcd1345lj");
        assertEquals(0d, account.getBalance(), DELTA);
    }
}
