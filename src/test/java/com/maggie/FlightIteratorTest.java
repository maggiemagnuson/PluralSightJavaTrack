package com.maggie;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by z001hqv on 12/15/16.
 */
public class FlightIteratorTest {

    @Test
    public void testHasNext(){
        CrewMember crewMember = new CrewMember("Test Crew");
        Passenger passenger = new Passenger();
        passenger.setFullname("Test Passenger");
        CrewMember [] crewMembers = {crewMember};
        Passenger[] passengers = {passenger};

        FlightIterator flightIterator = new FlightIterator(crewMembers, passengers);
        assertTrue(flightIterator.hasNext());
    }

    @Test
    public void testNext(){
        CrewMember crewMember = new CrewMember("Test Crew");
        Passenger passenger = new Passenger();
        passenger.setFullname("Test Passenger");
        CrewMember [] crewMembers = {crewMember};
        Passenger[] passengers = {passenger};

        FlightIterator flightIterator = new FlightIterator(crewMembers, passengers);

        assertEquals("Test Crew", flightIterator.next().getFullname());
    }

}
