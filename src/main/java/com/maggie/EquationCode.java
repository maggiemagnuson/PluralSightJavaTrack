package com.maggie;

/**
 * Created by z001hqv on 12/14/16.
 */

// This will only be available in the com.maggie package.
enum EquationCode {
    ADD,
    SUBTRACT,
    MULIPLY,
    DIVIDE;

    public double calculate(double x, double y){
        switch (this){
            case ADD:
                return x + y;
            case SUBTRACT:
                return x - y;
            case MULIPLY:
                return x * y;
            case DIVIDE:
                return (x != 0 & y != 0 )? x / y : 0.0d;
            default:
                throw new AssertionError("Unknown operations " + this);
        }
    }

}
