package com.maggie;

/**
 * Created by z001hqv on 12/14/16.
 */
public class MathEquation {

    private double leftVal;
    private double rightVal;
    private EquationCode opCode;

    // Need to have this since a new signature was created.
    public MathEquation(){}

    public MathEquation(double leftVal, double rightVal, EquationCode opCode){
        this.leftVal=leftVal;
        this.rightVal=rightVal;
        this.opCode=opCode;
    }

    public double getLeftVal() {
        return leftVal;
    }

    public void setLeftVal(double leftVal) {
        this.leftVal = leftVal;
    }

    public double getRightVal() {
        return rightVal;
    }

    public void setRightVal(double rightVal) {
        this.rightVal = rightVal;
    }

    public EquationCode getOpCode() {
        return opCode;
    }

    public void setOpCode(EquationCode opCode) {
        this.opCode = opCode;
    }

    public double execute(){
        return this.opCode.calculate(this.getLeftVal(), this.rightVal);
    }

}
