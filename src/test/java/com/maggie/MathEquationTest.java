package com.maggie;


import junit.framework.TestCase;
/**
 * Created by z001hqv on 12/14/16.
 */
public class MathEquationTest extends TestCase{
    protected MathEquation equation;

    protected void setUp(){
        equation = new MathEquation();
        equation.setLeftVal(10.0d);
        equation.setRightVal(10.0d);
    }


    public void testAdd(){
        equation.setOpCode(EquationCode.ADD);
        assertTrue(equation.execute() == 20.0d);
    }

    public void testSubtract(){
        equation.setOpCode(EquationCode.SUBTRACT);
        assertTrue(equation.execute() == 0);
    }

    public void testMultiply(){
        equation.setOpCode(EquationCode.MULIPLY);
        assertTrue(equation.execute() == 100.0d);
    }

    public void testDivide(){
        equation.setOpCode(EquationCode.DIVIDE);
        assertTrue(equation.execute() == 1.0d);
    }

    public void testLeftVal(){
        equation.setLeftVal(99.9d);
        assertEquals(99.9d, equation.getLeftVal());
    }

    public void testRightVal(){
        equation.setRightVal(99.9d);
        assertEquals(99.9d, equation.getRightVal());
    }

    public void testOpCode(){
        equation.setOpCode(EquationCode.ADD);
        assertEquals(EquationCode.ADD, equation.getOpCode());
    }

}
