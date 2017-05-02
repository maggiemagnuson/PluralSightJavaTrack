package com.maggie;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by z001hqv on 12/18/16.
 */
public class CargoFlightTest {

    protected CargoFlight cargoFlight;

    @Before
    public void setUp(){
        cargoFlight = new CargoFlight();
    }

    @Test
    public void testAddPassenger(){
        try {
            cargoFlight.addPassenger(2.0f, 5.0f, 10.0f);
            assertTrue("Cargo space " + cargoFlight.getUsedCargoSpace() + " should not exceed " + cargoFlight.getMaxCargoSpace(),
                    cargoFlight.getUsedCargoSpace() <= cargoFlight.getMaxCargoSpace());
        } catch (Exception e){
            Assert.fail("Passenger should be added:" + "Cargo space " + cargoFlight.getUsedCargoSpace() + " should not exceed " + cargoFlight.getMaxCargoSpace());
        }
    }

    @Test
    public void testAddPassengerException(){
        try{
            cargoFlight.addPassenger(100.0f, 100.0f, 100.0f);
            Assert.fail("Should throw out of space exception [Cargo Space=" + cargoFlight.getUsedCargoSpace() + " Max Cargo Space=" + cargoFlight.getMaxCargoSpace() + "]");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Maximum cargo space exceeded");
        }
    }

    @Test
    public void testSeats(){
        CargoFlight cargoFlight = new CargoFlight();

        assertEquals(12, cargoFlight.getSeats());
    }

    @Test
    public void testFlightNumberConstructor(){
        CargoFlight cargoFlight = new CargoFlight(222);
        assertEquals(222, cargoFlight.getFlightNumber());
    }
}
