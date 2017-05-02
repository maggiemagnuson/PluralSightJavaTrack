package com.maggie;

/**
 * Created by z001hqv on 12/22/16.
 */
public class CargoPilot extends Pilot {

    @Override
    public boolean canAccept(Flight flight){
        return flight instanceof CargoFlight;
    }
}
