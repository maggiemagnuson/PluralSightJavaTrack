package com.maggie;

/**
 * Created by z001hqv on 12/21/16.
 */
public abstract class Pilot {

    private Flight currentFlight;

    public Flight getCurrentFlight() {
        return currentFlight;
    }

    public void fly(Flight f){
        if (canAccept(f))
            currentFlight = f;
        else
            throw new RuntimeException("Pilot cannot accept flight");
    }

    public abstract boolean canAccept(Flight f);

}
