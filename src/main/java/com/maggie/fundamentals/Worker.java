package com.maggie.fundamentals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by z001hqv on 2/17/17.
 */
public class Worker implements Runnable {
    private BankAccount account;

    public Worker(BankAccount account){
        this.account = account;
    }

    @Override
    public void run(){
        System.out.println("inside run");
        for (int i=0; i<10; i++){
            double startBalance = account.getBalance();
            account.deposit(10);
            double endBalance = account.getBalance();
            System.out.println("endBalance: " + endBalance + " startBalance: " + startBalance
            + " Thread: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);

        BankAccount account = new BankAccount(100);
        for (int i=0; i<5; i++) {
            Worker worker = new Worker(account);
            es.submit(worker);
        }

        try{
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
