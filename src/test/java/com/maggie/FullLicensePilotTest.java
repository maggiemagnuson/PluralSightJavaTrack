package com.maggie;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by z001hqv on 12/22/16.
 */
public class FullLicensePilotTest {

    @Test
    public void testCanAcceptCargoFlight(){
        Flight flight = new CargoFlight();
        FullLicensePilot pilot = new FullLicensePilot();

        assertTrue("Full pilot can accept cargo flight", pilot.canAccept(flight));
    }

    @Test
    public void testCanAcceptPassengerFlight(){
        Flight flight = new Flight();
        flight.addPassenger();

        FullLicensePilot pilot = new FullLicensePilot();

        assertTrue("Full cannot accept passengers", pilot.canAccept(flight));
    }
}
