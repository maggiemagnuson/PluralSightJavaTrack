package com.maggie;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by z001hqv on 12/14/16.
 */
public class FlightTest {

    protected Flight flight1;
    protected Flight flight2;
    protected Flight flight;

    @Before
    public void setUp() {
        flight1 = new Flight(10);
        flight2 = new Flight(20);
        flight = new Flight(30);
    }

    @Test
    public void testFlightSwapFlight1() {
        flight1.swapFlightNumbers(flight1, flight2);
        assertEquals(flight1.getFlightNumber(), 20);
    }

    @Test
    public void testFlightSwapFlight() {
        flight1.swapFlightNumbers(flight1, flight2);
        assertEquals(flight2.getFlightNumber(), 10);
    }

    @Test
    public void testAddingPassenger() throws Exception {
        //Reset passengers and bags for unit test.
        resetStaticValue();

        flight1.addPassenger();
        assertEquals(flight1.getPassengers(), 1);
    }

    @Test(expected = Exception.class)
    public void testShouldThrowException() {
        //Reset passengers and bags for unit test.
        resetStaticValue();

        flight.setPassengers(150);
        flight.addPassenger();
    }

    @Test
    public void testExceptionMessage() {
        //Reset passengers and bags for unit test.
        resetStaticValue();

        try {
            flight.setPassengers(150);
            flight.addPassenger();
            Assert.fail("Should throw too maximum capacity exception");
        } catch(Exception e){
            assertEquals(e.getMessage(), "Flight reached maximum capacity");
        }
    }


    @Test
    public void testPassengerWithCarryOns(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        flight.addPassenger(3);
        assertTrue("Passengers " + flight.getPassengers() + " should be less than seats " + flight.getSeats(), flight.getPassengers() < flight.getSeats());
    }

    @Test
    public void testTooManyPassengersWithCarryOns(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        flight.setPassengers(200);
        try{
            flight.addPassenger(2);
            Assert.fail("Should throw maximum capacity exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Flight reached maximum capacity");
        }
    }

    @Test
    public void testNotEnoughCarryOnSpace(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        try{
            flight.addPassenger(flight.getSeats()*2+1);
            Assert.fail("Should throw exception.");
        }catch (Exception e){
            assertEquals(e.getMessage(), "Flight reached maximum carry-on capacity");
        }
    }

    @Test
    public void testPassengerWithCarryOnAndCheckedBags(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        System.out.println(flight.getPassengers());
        try{
            flight.addPassenger(2,3);
            assertTrue("Passengers " + flight.getPassengers() + " should be less than seats " + flight.getSeats(), flight.getPassengers() < flight.getSeats());
        } catch (Exception e){
            Assert.fail("Passengers " + flight.getPassengers() + " should not exceed seats (" + flight.getSeats() +")");
        }
    }

    @Test
    public void testCheckedBags(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        flight.addPassenger(0,2);
        flight1.addPassenger(1,3);
        assertEquals(flight2.getTotalBags(), 5);
    }

    @Test
    public void testCarryOnBags(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        flight.addPassenger(3,2);
        flight1.addPassenger(4,3);
        assertEquals(flight2.getTotalCarryOns(), 7);
    }

    @Test
    public void testStaticPassengers() {
        //Reset passengers and bags for unit test.
        resetStaticValue();

        try {
            flight.setPassengers(158);
            flight.addPassenger();
            flight1.addPassenger();
            flight2.addPassenger();
            Assert.fail("Should throw maximum capacity exception");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Flight reached maximum capacity");
        }
    }

    @Test
    public void testAddPassengerCarryOnCheckedBagHasSeating() {
        //Reset passengers and bags for unit test.
        resetStaticValue();

        try {
            flight.addPassenger(2,2);
            assertEquals(flight.getPassengers(), 1);
        } catch (Exception e){
            Assert.fail("Passengers " + flight.getPassengers() + " should not exceed seats (" + flight.getSeats() +")");
        }
    }

    @Test
    public void testAddPassengerCarryOnCheckedBagNoSeatingException() {
        //Reset passengers and bags for unit test.
        resetStaticValue();

        try {
            flight.setPassengers(150);
            flight.addPassenger(2,2);
            Assert.fail("Passengers " + flight.getPassengers() + " should exceed seats (" + flight.getSeats() +")");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Flight reached maximum capacity");
        }
    }

    @Test
    public void testAddPassengerCarryOnCheckedBagNoCarryOnException() {
        //Reset passengers and bags for unit test.
        resetStaticValue();

        try {
            flight.setTotalCarryOns(flight.getSeats()*2+1);
            flight.addPassenger(2,2);
            Assert.fail("Carry ons " + flight.getTotalCarryOns() + " should exceed seats (" + flight.getSeats()*2 +")");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Flight reached maximum carry-on capacity");
        }
    }

    @Test
    public void testAddPassengerPassengerObject(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        Passenger passenger = new Passenger(2,1);
        flight.addPassenger(passenger);
        assertEquals(flight.getPassengers(), 1);
    }

    @Test
    public void testPassengerObjectBagsChecked(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        Passenger passenger = new Passenger(2,1);
        flight.addPassenger(passenger);
        assertEquals(flight.getTotalBags(), 2);
    }

    @Test
    public void testPassengerObjectCarryOnBags(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        Passenger passenger = new Passenger(2,1);
        flight.addPassenger(passenger);
        assertEquals(flight.getTotalCarryOns(), 1);
    }

    @Test
    public void testAddPassengerList(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        Passenger foo = new Passenger(1,1);
        Passenger bar = new Passenger(2,1);
        Passenger foobar = new Passenger(0,1);

        flight.addPassenger(foo, bar, foobar);
        assertEquals(flight.getPassengers(),3);
    }

    @Test
    public void testAddPassengerListCheckBags(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        Passenger foo = new Passenger(1,1);
        Passenger bar = new Passenger(2,1);
        Passenger foobar = new Passenger(0,1);

        flight.addPassenger(foo, bar, foobar);
        assertEquals(flight.getTotalBags(),3);
    }

    @Test
    public void testAddPassengerListCarryOnBags(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        Passenger foo = new Passenger(1,1);
        Passenger bar = new Passenger(2,1);
        Passenger foobar = new Passenger(0,1);

        flight.addPassenger(foo, bar, foobar);
        assertEquals(flight.getTotalCarryOns(),3);
    }


    @Test
    public void testAddPassengerListPassengerException(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        flight.setPassengers(148);
        Passenger foo = new Passenger(1,1);
        Passenger bar = new Passenger(2,1);
        Passenger foobar = new Passenger(0,1);

        try {
            flight.addPassenger(foo, bar, foobar);
            Assert.fail("Passengers " + flight.getPassengers() + " should exceed seats (" + flight.getSeats() +")");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Flight reached maximum capacity");
        }

    }

    @Test
    public void testAddPassengerListCarryOnException(){
        //Reset passengers and bags for unit test.
        resetStaticValue();

        flight.setTotalCarryOns(flight.getSeats()*2-2);
        Passenger foo = new Passenger(1,1);
        Passenger bar = new Passenger(2,1);
        Passenger foobar = new Passenger(0,1);

        try {
            flight.addPassenger(foo, bar, foobar);
            Assert.fail("Carry ons " + flight.getTotalCarryOns() + " should exceed seats (" + flight.getSeats()*2 +")");
        } catch (Exception e){
            assertEquals(e.getMessage(), "Flight reached maximum carry-on capacity");
        }

    }

    @Test
    public void testFlightClass(){
        flight.setFlightClass(2);
        assertEquals(2, flight.getFlightClass());
    }

    @Test
    public void testNegativeFlightClass(){
        flight.setFlightClass(22);
        assertNotEquals(2, flight.getFlightClass());
    }

    @Test
    public void testObjectEqualityFlightNumber(){
        Flight flight1 = new Flight(175);
        Flight flight2 = new Flight(175);
        assertTrue("Flight Number should be equal", flight1.equals(flight2));
    }

    @Test
    public void testObjectInequalityFlightNumber(){
        Flight flight1 = new Flight(175);
        Flight flight2 = new Flight(177);
        assertFalse("Flight Number should not be equal", flight1.equals(flight2));
    }

    @Test
    public void testObjectEqualityFlightClass(){
        Flight flight1 = new Flight();
        Flight flight2 = new Flight();
        flight1.setFlightClass(1);
        flight2.setFlightClass(1);
        assertTrue("Flight Class should be equal", flight1.equals(flight2));
    }

    @Test
    public void testObjectInequalityFlightClass(){
        Flight flight1 = new Flight();
        Flight flight2 = new Flight();
        flight1.setFlightClass(1);
        flight2.setFlightClass(2);
        assertFalse("Flight Class should not be equal", flight1.equals(flight2));
    }

    @Test
    public void testObjectInequalityObjectType(){
        Flight flight = new Flight(175);
        Passenger passenger = new Passenger(1,2);
        assertFalse("Two differing objects types should not be equal", flight.equals(passenger));
    }

    @Test
    public void testSuperObjectEquality(){
        Flight flight = new Flight();
        Flight flight2 = flight;
        assertTrue("Flights should be equal", flight.equals(flight2));
    }

    @Test
    public void testResetPassengers(){
        Flight flight = new Flight();
        flight.setPassengers(22);
        flight.resetPassengers();
        assertEquals(0, flight.getPassengers());
    }

    public void resetStaticValue(){
        flight.resetPassengers();
        flight.setTotalBags(0);
        flight.setTotalCarryOns(0);
    }

    @Test
    public void testFlightTimeComparison(){
        Flight lax045 = new Flight();
        lax045.setFlightTime(45);
        lax045.setFlightName("lax045");

        Flight slc015 = new Flight();
        slc015.setFlightTime(15);
        slc015.setFlightName("slc015");

        Flight nyc030 = new Flight();
        nyc030.setFlightTime(30);
        nyc030.setFlightName("nyc030");

        Flight[] flights = {lax045, slc015, nyc030};

        Arrays.sort(flights);

        List<String> expectedArray = new ArrayList<String>(Arrays.asList("slc015", "nyc030", "lax045"));
        List<String> actualArray = new ArrayList<String>();

        for (int i = 0; i < flights.length; i++){
            //System.out.println(flights[i].getFlightName());
            actualArray.add(flights[i].getFlightName());
        }

        assertArrayEquals(expectedArray.toArray(), actualArray.toArray());

    }

    @Test
    public void testPersonList(){
        Flight lax045 = new Flight();

        CrewMember m1 = new CrewMember("Pilot Patty");
        CrewMember m2 = new CrewMember("Copilot Karl");
        CrewMember m3 = new CrewMember("Marshal Mary");
        CrewMember [] crew = {m1,m2,m3};
        lax045.setCrewMembersList(crew);

        Passenger bob = new Passenger();
        bob.setFullname("Bob Brown");
        Passenger jane = new Passenger();
        jane.setFullname("Jane Fonda");
        Passenger steve = new Passenger();
        steve.setFullname("Steve Martin");
        Passenger lisa = new Passenger();
        lisa.setFullname("Lisa Simpson");
        Passenger [] passengers = {bob, jane, steve, lisa};
        lax045.setPassengerList(passengers);

        List<String> expectedArray = new ArrayList<String>(Arrays.asList("Pilot Patty", "Copilot Karl", "Marshal Mary",
                "Bob Brown", "Jane Fonda", "Steve Martin", "Lisa Simpson"));
        List<String> actualArray = new ArrayList<String>();


        for (Person p: lax045){
            actualArray.add(p.getFullname());
        }

        assertArrayEquals(expectedArray.toArray(), actualArray.toArray());
    }

}