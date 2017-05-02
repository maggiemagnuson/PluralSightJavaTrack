package com.maggie;

import junit.framework.TestCase;

/**
 * Created by z001hqv on 12/14/16.
 */
public class MainTest extends TestCase {
    protected Main main;

    public void setUp(){
        main = new Main();
    }

    public void testCreate(){
        MathEquation mathEquation = main.create(10.0d, 10.0d, EquationCode.ADD);
        assertNotNull(mathEquation);
    }
}
