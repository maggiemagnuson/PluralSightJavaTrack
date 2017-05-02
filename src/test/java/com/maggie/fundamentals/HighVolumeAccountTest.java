package com.maggie.fundamentals;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by z001hqv on 2/19/17.
 */
public class HighVolumeAccountTest {
    private static final double DELTA = 1e-15;

    @Test
    public void testDepositThread(){
        HighVolumeAccount hva1 = new HighVolumeAccount("1",0d);
        HighVolumeAccount hva2 = new HighVolumeAccount("2",100d);
        HighVolumeAccount hva3 = new HighVolumeAccount("3", 200);

        double[] deposits = {12.0, 123.0, 126.0, 67.0, 1378.0, 367.0, 4.0, 32.0,
                12.0, 23.0, 16.0, 7.0, 178.0, 67.0, 48.0, 132.0,
                11.0, 99.0, 12.0, 87.0, 13.0, 37.0, 34.0, 432.0};

        hva1.setDailyDeposits(deposits);
        hva2.setDailyDeposits(deposits);
        hva3.setDailyDeposits(deposits);

        HighVolumeAccount[] accounts = {hva1, hva2, hva3};

        ExecutorService es = Executors.newFixedThreadPool(3);

        for (int i=0; i < accounts.length; i++){
            //System.out.println(accounts[i].getClass());
            es.submit(accounts[i]);
        }

        try{
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }


        System.out.println(hva1.getBalance());
        System.out.println(hva2.getBalance());
        System.out.println(hva3.getBalance());

        assertEquals(10251.0, hva1.getBalance()+hva2.getBalance()+hva3.getBalance(), DELTA);
    }

    @Test
    public void testWithdrawalThread(){
        HighVolumeAccount hva1 = new HighVolumeAccount("1",4000d);
        HighVolumeAccount hva2 = new HighVolumeAccount("2",4000d);
        HighVolumeAccount hva3 = new HighVolumeAccount("3", 4000d);

        double[] withdrawal = {12.0, 123.0, 126.0, 67.0, 1378.0, 367.0, 4.0, 32.0,
                12.0, 23.0, 16.0, 7.0, 178.0, 67.0, 48.0, 132.0,
                11.0, 99.0, 12.0, 87.0, 13.0, 37.0, 34.0, 432.0};

        hva1.setDailyWithdrawals(withdrawal);
        hva2.setDailyWithdrawals(withdrawal);
        hva3.setDailyWithdrawals(withdrawal);

        HighVolumeAccount[] accounts = {hva1, hva2, hva3};

        ExecutorService es = Executors.newFixedThreadPool(3);

        for (int i=0; i < accounts.length; i++){
            es.submit(accounts[i]);
        }

        try{
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }


        System.out.println(hva1.getBalance());
        System.out.println(hva2.getBalance());
        System.out.println(hva3.getBalance());

        assertEquals(2049.0, hva1.getBalance()+hva2.getBalance()+hva3.getBalance(), DELTA);
    }
}
