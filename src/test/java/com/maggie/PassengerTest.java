package com.maggie;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by z001hqv on 12/15/16.
 */
public class PassengerTest {

    @Test
    public void testCarryOnBagsConstructor(){
        Passenger passenger = new Passenger(1,2);
        assertEquals(passenger.getCarryOnBags(), 2);
    }

    @Test
    public void testCheckedBagsConstructor(){
        Passenger passenger = new Passenger(1,2);
        assertEquals(passenger.getCheckedBags(), 1);
    }

    @Test
    public void testCarryOnBags(){
        Passenger passenger = new Passenger();
        passenger.setCarryOnBags(2);
        assertEquals(2, passenger.getCarryOnBags());
    }

    @Test
    public void testCheckedBags(){
        Passenger passenger = new Passenger();
        passenger.setCheckedBags(3);
        assertEquals(3, passenger.getCheckedBags());
    }

    @Test
    public void testHighestPriority(){
        Passenger bob = new Passenger();
        bob.setLevelAndDays(1, 100);

        Passenger jane = new Passenger();
        jane.setLevelAndDays(3,70);

        assertEquals(-1, jane.compareTo(bob));
    }

    @Test
    public void testNegativeHighestPriority(){
        Passenger bob = new Passenger();
        bob.setLevelAndDays(1, 100);

        Passenger jane = new Passenger();
        jane.setLevelAndDays(3,70);

        assertNotEquals(-1, bob.compareTo(jane));
    }

    @Test
    public void testSecondPriority(){
        Passenger bob = new Passenger();
        bob.setLevelAndDays(1, 100);

        Passenger jane = new Passenger();
        jane.setLevelAndDays(2,70);

        assertEquals(-1, jane.compareTo(bob));
    }

    @Test
    public void testNegativeSecondPriority(){
        Passenger bob = new Passenger();
        bob.setLevelAndDays(3, 100);

        Passenger jane = new Passenger();
        jane.setLevelAndDays(2,70);

        assertNotEquals(1, bob.compareTo(jane));
    }

    @Test
    public void testPriorityByDays(){
        Passenger bob = new Passenger();
        bob.setLevelAndDays(2, 100);

        Passenger jane = new Passenger();
        jane.setLevelAndDays(2,70);

        assertEquals(-1, bob.compareTo(jane));
    }

    @Test
    public void testPriorityByDaysEqual(){
        Passenger bob = new Passenger();
        bob.setLevelAndDays(2, 100);

        Passenger jane = new Passenger();
        jane.setLevelAndDays(2,100);

        assertEquals(0, bob.compareTo(jane));
    }

    @Test
    public void testSamePriorityLessDays(){
        Passenger bob = new Passenger();
        bob.setLevelAndDays(2, 100);

        Passenger jane = new Passenger();
        jane.setLevelAndDays(2,90);

        assertEquals(1, jane.compareTo(bob));
    }

    @Test
    public void testPrioritySort(){
        Passenger bob = new Passenger();
        bob.setLevelAndDays(1,100);
        bob.setFullname("Bob Brown");
        Passenger jane = new Passenger();
        jane.setLevelAndDays(1, 90);
        jane.setFullname("Jane Fonda");
        Passenger steve = new Passenger();
        steve.setLevelAndDays(2,180);
        steve.setFullname("Steve Martin");
        Passenger lisa = new Passenger();
        lisa.setLevelAndDays(3,730);
        lisa.setFullname("Lisa Simpson");

        // Priority = Lisa, Steve, Bob, Jane

        Passenger[] passengers = {bob, jane, steve, lisa};

        Arrays.sort(passengers);

        List<String> expectedArray = new ArrayList<String>(Arrays.asList("Lisa Simpson", "Steve Martin", "Bob Brown", "Jane Fonda"));
        List<String> actualArray = new ArrayList<String>();

        for (int i = 0; i < passengers.length; i++){
            actualArray.add(passengers[i].getFullname());
        }

        assertArrayEquals(expectedArray.toArray(), actualArray.toArray());
    }

}
