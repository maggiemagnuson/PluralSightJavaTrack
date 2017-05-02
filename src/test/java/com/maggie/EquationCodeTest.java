package com.maggie;

import junit.framework.TestCase;

/**
 * Created by z001hqv on 12/14/16.
 */
public class EquationCodeTest extends TestCase {

    public void testAdd(){
        assertTrue (EquationCode.ADD.calculate(10.0d, 10.0d) == 20.0d);
    }

    public void testSubtract(){
        assertTrue(EquationCode.SUBTRACT.calculate(10.0d, 10.0d) == 0);
    }

    public void testMultiply(){
        assertTrue(EquationCode.MULIPLY.calculate(10.0d, 10.0d) == 100.0d);
    }

    public void testDivide(){
        assertTrue(EquationCode.DIVIDE.calculate(10.0d,10.0d) == 1.0d);
    }

    public void testDivideByZeroX(){
        assertTrue(EquationCode.DIVIDE.calculate(0.0d, 10.0d) == 0.0d);
    }

    public void testDivideByZeroY(){
        assertTrue(EquationCode.DIVIDE.calculate(10.0d, 0.0d) == 0.0d);
    }

}
