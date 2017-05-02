package com.maggie;
/**
 * Created by z001hqv on 12/13/16.
 */
public class Main {

    public static void main(String[] args){
        MathEquation[] equations = new MathEquation[4];
        equations[0] = create(100.d, 50.d, EquationCode.DIVIDE);
        equations[1] = create(25.d, 92.0, EquationCode.ADD);
        equations[2] = create(225.0d, 17.0d, EquationCode.SUBTRACT);
        equations[3] = create(11.0d, 3.0d, EquationCode.MULIPLY);

        for (MathEquation equation: equations){
            System.out.println("result = " + equation.execute());
        }
    }

    public static MathEquation create(double leftVal, double rightVal, EquationCode opCode){
        return new MathEquation(leftVal, rightVal, opCode);
    }
}

