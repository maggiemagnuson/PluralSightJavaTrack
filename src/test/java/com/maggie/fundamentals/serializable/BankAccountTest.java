package com.maggie.fundamentals.serializable;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by z001hqv on 3/20/17.
 */
public class BankAccountTest {
    private static final Logger LOG = Logger.getLogger(BankAccountTest.class);
    private static final double DELTA = 1e-15;

    protected BankAccount ba;

    @Before
    public void setUp(){
        ba = new BankAccount("abcd1234%", 500f);
    }

    @Test
    public void testDeposit(){
        ba.deposit(100f);
        assertEquals(600f, ba.getBalance(), DELTA);
    }

    @Test
    public void testWithdrawal(){
        ba.withdrawal(100f);
        assertEquals(400f, ba.getBalance(), DELTA);
    }

    @Test
    public void testOverdraft(){

        try{
            ba.withdrawal(501f);
            Assert.fail("Should not allow overdraft withdrawal");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Insufficient funds for withdrawal");
        }

    }

    @Test
    public void testSaveAccount(){
        LOG.info("Balance [" + ba.getBalance() + "]");
        ba.withdrawal(199.34f);
        LOG.info("Balance [" + ba.getBalance() + "]");
        ba.deposit(364.59f);
        LOG.info("Balance [" + ba.getBalance() + "]");
        ba.saveAccount(ba, "/Users/z001hqv/GitProjects/PluralSightJavaTrack/data/bankaccount.dat");
    }

    @Test
    public void testReadAccount(){
       BankAccount bankAccount = ba.readAccount("/Users/z001hqv/GitProjects/PluralSightJavaTrack/data/bankaccount.dat");
       LOG.info("[AccountId-> " + bankAccount.getId() + " :: Balance-> " + bankAccount.getBalance() + " :: LstTransType-> "  + bankAccount.getLastTxType() + " :: LstTransAmt-> " + bankAccount.getLastTxAmt() + " isVip-> " + bankAccount.isVip() + "]");
    }

}
