package Expressions;

import Exceptions.UndefinedVariableException;
import Instructions.Instruction;

public class IntegerLiteral extends Expression {

    int value;
    public IntegerLiteral(int value) {
        this.value = value;
    }
    public String toString() {
        return Integer.toString(value);
    }

    public int calculate(Instruction parentBlock) throws UndefinedVariableException, ArithmeticException{
        return value;
    }
}
