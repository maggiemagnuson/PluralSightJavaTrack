package com.maggie.fundamentals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by z001hqv on 2/17/17.
 */
public class TxPromoWorker extends TxWorker {

    public TxPromoWorker(BankAccount account, char txType, double amt) {
        super(account, txType, amt);
    }

    @Override
    public void run(){
        if (txType == 'w'){
            account.withdrawal(amt);
        } else if (txType == 'd') {
            //We want to lock this entire account so another
            //lock isn't acquired by a thread before we apply
            //the bonus amount. What could be affected is when
            //the if-statement does the getBalance method if a
            //withdrawal happened.
            synchronized (account) {
                account.deposit(amt);
                if (account.getBalance() > 500) {
                    double bonus = amt * 0.1;
                    account.deposit(bonus);
                }
           }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        BankAccount account = new BankAccount(500);
        TxWorker[] workers = {new TxPromoWorker(account, 'w', 10d), //490
                new TxPromoWorker(account, 'w', 10d), //480
                new TxPromoWorker(account, 'd', 10d), //490
                new TxPromoWorker(account, 'd', 10d), //500
                new TxPromoWorker(account, 'd', 10d), //510 + 1 = 511
                new TxPromoWorker(account, 'd', 10d), //521 + 1 = 522
                new TxPromoWorker(account, 'w', 10d), //512
                new TxPromoWorker(account, 'd', 10d), //522 + 1 = 523
                new TxPromoWorker(account, 'd', 10d), //533 + 1 = 534
                new TxPromoWorker(account, 'w', 10d), //524
                new TxPromoWorker(account, 'w', 10d), //514
                new TxPromoWorker(account, 'd', 10d), //524 + 1 = 525
                new TxPromoWorker(account, 'w', 10d), //515
                new TxPromoWorker(account, 'd', 10d)  //525 + 1 = 526
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
