package Expressions;
import Exceptions.UndefinedVariableException;
import Instructions.Instruction;
import Instructions.Calculate;

public interface Expression {
    int calculate(Instruction parent) throws UndefinedVariableException, ArithmeticException;
}
