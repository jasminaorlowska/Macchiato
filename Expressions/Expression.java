package Expressions;
import Exceptions.UndefinedVariableException;
import Instructions.Instruction;

public abstract class Expression {
    abstract public int calculate(Instruction parent) throws UndefinedVariableException, ArithmeticException;

    public static Sum sum(Expression e1, Expression e2) {
        return new Sum(e1, e2);
    }
    public static Division divide(Expression e1, Expression e2) {
        return new Division(e1,e2);
    }
    public static IntegerLiteral constant(int value) {
        return new IntegerLiteral(value);
    }
    public static Multiplication multiply(Expression e1, Expression e2) {
        return new Multiplication(e1,e2);
    }
    public static Variable var(char name) {
        return new Variable(name);
    }
    public static Subtraction subtract(Expression e1, Expression e2) {
        return new Subtraction(e1,e2);
    }
}
