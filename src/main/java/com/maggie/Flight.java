package com.maggie;

import java.util.HashMap;
import java.util.Iterator;


/**
 * Created by z001hqv on 12/14/16.
 */
public class Flight implements Comparable, Iterable<Person>{

    private int flightNumber;
    private int flightClass;
    private int seats = 150;
    private static int passengers;
    private int maxCarryOns = seats * 2;
    private static int totalCarryOns;
    private static int totalBags;
    private int flightTime;
    private String flightName;
    private Passenger[] passengerList;
    private CrewMember[] crewMembersList;

    public Flight(int flightNumber){
        this.flightNumber=flightNumber;
    }

    public Flight(){ }

    public static int getTotalCarryOns() {
        return totalCarryOns;
    }

    public static void setTotalCarryOns(int totalCarryOns) {
        Flight.totalCarryOns = totalCarryOns;
    }

    public static int getTotalBags() {
        return totalBags;
    }

    public static void setTotalBags(int totalBags) {
        Flight.totalBags = totalBags;
    }

    public int getSeats() {
        return seats;
    }

    //public void setSeats(int seats) {
    //    this.seats = seats;
    //}

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;

    }

    public int getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(int flightClass) {
        this.flightClass = flightClass;
    }

    public int getFlightTime() {
        return flightTime;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public void setFlightTime(int flightTime) {
        this.flightTime = flightTime;
    }

    public Passenger[] getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(Passenger[] passengerList) {
        this.passengerList = passengerList;
    }

    public CrewMember[] getCrewMembersList() {
        return crewMembersList;
    }

    public void setCrewMembersList(CrewMember[] crewMembersList) {
        this.crewMembersList = crewMembersList;
    }

    static void resetPassengers(){
        passengers = 0;
    }

    public int compareTo(Object o){
        Flight flight = (Flight) o;
        return flightTime - flight.flightTime;
    }

    @Override
    public boolean equals(Object o) {
        if (super.equals(o))
            return true;

        if (o instanceof Flight) {
            Flight other = (Flight) o;
            return flightNumber == other.flightNumber &&
                    flightClass == other.flightClass;
        } else
            return false;
    }

    public Iterator<Person> iterator(){
        return new FlightIterator(crewMembersList, passengerList);
    }

    public void swapFlightNumbers(Flight flight1, Flight flight2){
        int k = flight1.getFlightNumber();
        flight1.setFlightNumber(flight2.getFlightNumber());
        flight2.setFlightNumber(k);
    }

    public void addPassenger() {
        if (hasSeating())
            passengers += 1;
        else {
            throw new RuntimeException("Flight reached maximum capacity");

        }
    }

    public void addPassenger(int carryOnBags){

        if(hasSeating()) {
            addPassenger();

            if (hasCarryOnSpace(carryOnBags)) {
                totalCarryOns += carryOnBags;
            } else {
                throw new RuntimeException("Flight reached maximum carry-on capacity");
            }
        } else{
            throw new RuntimeException("Flight reached maximum capacity");
        }
    }

    public void addPassenger(int carryOnBags, int checkedBags){

        if (hasSeating() && hasCarryOnSpace(carryOnBags)){
            addPassenger();
            totalCarryOns += carryOnBags;
            totalBags += checkedBags;
        } else if (!hasSeating()){
            throw new RuntimeException("Flight reached maximum capacity");
        } else {
            throw new RuntimeException("Flight reached maximum carry-on capacity");
        }
    }

    public void addPassenger(Passenger passenger){
        addPassenger(passenger.getCarryOnBags(), passenger.getCheckedBags());
    }

    public void addPassenger(Passenger ... list){
        for (Passenger passenger: list){
            addPassenger(passenger);
        }
    }

    private boolean hasSeating(){
        return passengers < getSeats();
    }

    private boolean hasCarryOnSpace(int carryOns){
        return totalCarryOns + carryOns <= maxCarryOns;
    }

}
