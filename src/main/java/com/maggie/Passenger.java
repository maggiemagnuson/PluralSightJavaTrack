package com.maggie;

/**
 * Created by z001hqv on 12/15/16.
 */
public class Passenger extends Person implements Comparable {

    private int checkedBags;
    private int carryOnBags;
    private  RewardProgram rewardProgram = new RewardProgram();

    public RewardProgram getRewardProgram(){
        return rewardProgram;
    }


    public Passenger(){}

    public Passenger(int checkedBags, int carryOnBags){
        this.checkedBags = checkedBags;
        this.carryOnBags = carryOnBags;
    }

    public int compareTo(Object o){
        Passenger p = (Passenger) o;

        if (rewardProgram.getMemberLevel() > p.rewardProgram.getMemberLevel())
            return -1; //Should come before, higher priority
        else if (rewardProgram.getMemberLevel() < p.rewardProgram.getMemberLevel())
            return 1; //Should come after, lower priority
        else
            if(rewardProgram.getMemberDays() > p.rewardProgram.getMemberDays())
                return -1;
            else if (rewardProgram.getMemberDays() < p.rewardProgram.getMemberDays())
                return 1;
            else
                return 0;
    }

    public void setLevelAndDays(int level, int days){
        this.rewardProgram.setMemberLevel(level);
        this.rewardProgram.setMemberDays(days);
    }

    public int getCheckedBags() {
        return checkedBags;
    }

    public void setCheckedBags(int checkedBags) {
        this.checkedBags = checkedBags;
    }

    public int getCarryOnBags() {
        return carryOnBags;
    }

    public void setCarryOnBags(int carryOnBags) {
        this.carryOnBags = carryOnBags;
    }

    public static class RewardProgram{
        private int memberLevel; //3 (highest priority, 2, 1
        private int memberDays;

        public int getMemberLevel() { return memberLevel; }
        public void setMemberLevel(int level){ this.memberLevel = level; }

        public int getMemberDays() { return memberDays; }
        public void setMemberDays(int days){ memberDays = days; }
    }


}
