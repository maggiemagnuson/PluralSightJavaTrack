package com.maggie.fundamentals;

/**
 * Created by z001hqv on 2/19/17.
 */
public final class HighVolumeAccount extends Account implements Runnable{

    private double[] deposits;
    private double[] withdrawals;

    public HighVolumeAccount (String id ){
        super(id);
    }

    public HighVolumeAccount (String id, double balance){
        super(id, balance);
    }

    public void setDailyDeposits(double[] deposits){
        this.deposits = deposits;
    }

    private double[] getDailyDeposit(){
        return this.deposits;
    }

    public void setDailyWithdrawals(double[] withdrawals){
        this.withdrawals = withdrawals;
    }

    public double[] getDailyWithdrawals(){
        return this.withdrawals;
    }

    @Override
    public void run(){

        try {
            //Make sure it's not null before checking length
            if (getDailyDeposit() != null && getDailyDeposit().length > 0) {
                for (double depositAmt : getDailyDeposit()) {
                    deposit(depositAmt);
                }
            }
            //Make sure it's not null before checking length
            if (getDailyWithdrawals() != null && getDailyWithdrawals().length > 0) {
                for (double withdrawalAmt : getDailyWithdrawals()) {
                    withdrawal(withdrawalAmt);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
