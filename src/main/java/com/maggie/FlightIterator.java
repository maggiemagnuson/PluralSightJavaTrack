package com.maggie;

import java.util.Iterator;

/**
 * Created by z001hqv on 1/19/17.
 */
public class FlightIterator implements Iterator<Person> {

    private CrewMember[] crewMembers;
    private  Passenger[] roster;
    private int index = 0;

    public FlightIterator(CrewMember[] crew, Passenger[] roster){
        this.crewMembers = crew;
        this.roster = roster;
    }

    public boolean hasNext(){
        //System.out.println(index);
        //System.out.println(crewMembers.length + roster.length);
        return index < (crewMembers.length + roster.length);
    }

    public Person next(){

        Person p = (index < crewMembers.length) ? crewMembers[index] :
                roster[index - crewMembers.length]; // if crewMembers=3 and index=3 then it would grab 0 element
        index++;
        return p;
    }
}
