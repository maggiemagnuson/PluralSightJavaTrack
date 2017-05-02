package com.maggie.fundamentals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by z001hqv on 2/17/17.
 */
public class WorkerSynchronized implements Runnable {
    private BankAccount account;

    public WorkerSynchronized(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            synchronized (account){
                double startBalance = account.getBalanceNonThreadSafe();
                account.deposit(10);
                double endBalance = account.getBalanceNonThreadSafe();
                System.out.println("endBalance: " + endBalance + " startBalance: " + startBalance
                        + " Thread: " + Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        BankAccount account = new BankAccount(100);

        for (int i = 0; i < 5; i++) {
            synchronized (account) {
                WorkerSynchronized worker = new WorkerSynchronized(account);
                es.submit(worker);
            }
        }

        try {
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
