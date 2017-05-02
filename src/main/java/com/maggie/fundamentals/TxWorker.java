package com.maggie.fundamentals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by z001hqv on 2/17/17.
 */
public class TxWorker implements Runnable {
    protected BankAccount account;
    protected char txType; // 'w' -> withdrawal 'd' -> deposit
    protected double amt;

    public TxWorker(BankAccount account, char txType, double amt){
        this.account = account;
        this.txType = txType;
        this.amt = amt;
    }

    @Override
    public void run() {
        if (txType == 'w')
            account.withdrawal(amt);
        else if (txType == 'd')
            account.deposit(amt);
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        BankAccount account = new BankAccount(100);
        TxWorker[] workers = {new TxWorker(account, 'w', 10d),
                new TxWorker(account, 'w', 10d),
                new TxWorker(account, 'd', 10d),
                new TxWorker(account, 'd', 10d),
                new TxWorker(account, 'd', 10d),
                new TxWorker(account, 'd', 10d),
                new TxWorker(account, 'w', 10d),
                new TxWorker(account, 'd', 10d),
                new TxWorker(account, 'd', 10d),
                new TxWorker(account, 'w', 10d),
                new TxWorker(account, 'w', 10d),
                new TxWorker(account, 'd', 10d),
                new TxWorker(account, 'w', 10d),
                new TxWorker(account, 'd', 10d)
        };

        for (TxWorker worker : workers){
            es.submit(worker);
        }
        //This needs to be here in order to ensure all threads have ended.
        try{
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(account.getBalance());
    }
}
