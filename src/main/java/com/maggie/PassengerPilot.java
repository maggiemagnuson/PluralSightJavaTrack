package com.maggie;

/**
 * Created by z001hqv on 12/22/16.
 */
public class PassengerPilot extends Pilot{

    @Override
    public boolean canAccept(Flight flight){
        System.out.println(flight.getClass());
        if (flight instanceof CargoFlight)
            return false;
        else
            return true;
    }
}
