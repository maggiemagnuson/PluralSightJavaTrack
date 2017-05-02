package com.maggie;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by z001hqv on 12/15/16.
 */
public class PersonTest {

    @Test
    public void testFullName(){
        Person p = new Person();
        p.setFullname("Test Fullname");
        assertEquals("Test Fullname", p.getFullname());
    }
}
