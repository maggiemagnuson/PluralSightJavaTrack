package com.maggie;

/**
 * Created by z001hqv on 12/18/16.
 */
public class CargoFlight extends Flight {

    private float maxCargoSpace = 1000.0f;
    private float usedCargoSpace = 0.0f;
    private int seats = 12;

    public CargoFlight(int flightNumber){
        super(flightNumber);
    }

    public CargoFlight(){}


    public void addPassenger(float h, float w, float d) throws Exception{
        float size = h * w * d;

        if (hasCargoSpace(size)){
            usedCargoSpace += size;
        }else{
            exceptionNoSpace();
        }
    }

    @Override
    public int getSeats() {
        return seats;
    }

    public float getUsedCargoSpace() {
        return usedCargoSpace;
    }

    public float getMaxCargoSpace() {
        return maxCargoSpace;
    }

    private boolean hasCargoSpace(float size){
        return usedCargoSpace + size <= maxCargoSpace;
    }

    private void exceptionNoSpace() throws Exception{
        throw new Exception("Maximum cargo space exceeded");
    }
}
