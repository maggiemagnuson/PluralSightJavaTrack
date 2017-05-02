package com.maggie;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by z001hqv on 12/22/16.
 */
public class PassengerPilotTest {

    @Test
    public void testCanAcceptFlight(){
        Flight flight = new Flight();
        PassengerPilot pilot = new PassengerPilot();

        assertTrue("Pilot can accept this flight", pilot.canAccept(flight));
    }

    @Test
    public void testCannotAcceptFlight(){
        Flight flight = new CargoFlight();
        PassengerPilot pilot = new PassengerPilot();

        assertFalse("Cargo pilot cannot accept passengers", pilot.canAccept(flight));
    }

    @Test
    public void testCanFly(){
        Flight flight = new Flight();
        PassengerPilot pilot = new PassengerPilot();

        try{
            pilot.fly(flight);
            assertEquals(flight, pilot.getCurrentFlight());
        } catch (Exception e){
            Assert.fail("Pilot should be able to fly()");
        }

    }

    @Test
    public void testCannotFly(){
        Flight flight = new CargoFlight();
        PassengerPilot pilot = new PassengerPilot();

        try{
            pilot.fly(flight);
            Assert.fail("Pilot should not be able to fly()");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Pilot cannot accept flight");
        }
    }
}
