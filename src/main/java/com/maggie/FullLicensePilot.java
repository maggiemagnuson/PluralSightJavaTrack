package com.maggie;

/**
 * Created by z001hqv on 12/22/16.
 */
public class FullLicensePilot extends Pilot {

    @Override
    public boolean canAccept(Flight flight){
        // He can fly anything.
        return true;
    }
}
