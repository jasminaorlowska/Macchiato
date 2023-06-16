package Expressions;
import Instructions.Instruction;
import Instructions.Calculate;
import Exceptions.UndefinedVariableException;

public class Sum extends Expression {

    private final Expression e1;
    private final Expression e2;

    public Sum(Expression e1, Expression e2) throws IllegalArgumentException{
        if (e1 == null || e2 == null) {
            throw new IllegalArgumentException("Expressions can't be null");
        }
        this.e1 = e1;
        this.e2 = e2;
    }

    public int calculate(Instruction parentBlock) throws UndefinedVariableException, ArithmeticException {
        Calculate c1 = new Calculate(e1,parentBlock);
        Calculate c2 = new Calculate(e2, parentBlock);
        int c1r = c1.run();
        int c2r = c2.run();
        return  c1r + c2r;
    }

    public String toString() {
        return "(" + e1  + " + " +  e2 + ")";
    }
}
