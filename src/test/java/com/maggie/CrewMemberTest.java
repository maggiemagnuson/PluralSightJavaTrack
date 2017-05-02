package com.maggie;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by z001hqv on 12/15/16.
 */
public class CrewMemberTest {

    @Test
    public void testFullName(){
        CrewMember crewMember = new CrewMember("Test Fullname");
        crewMember.setFullname("Test Fullname");
        assertEquals("Test Fullname", crewMember.getFullname());
    }
}
