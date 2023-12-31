package Expressions;
import Exceptions.UndefinedVariableException;
import Instructions.Instruction;
import Instructions.Calculate;

public class Modulo extends Expression {

    private final Expression e1;
    private final Expression e2;

    public Modulo(Expression e1, Expression e2) throws IllegalArgumentException{
        checkArguments(e1, e2);
        this.e1 = e1;
        this.e2 = e2;
    }

    public int calculate(Instruction parentBlock) throws UndefinedVariableException, ArithmeticException{
        if (e2.calculate(parentBlock) == 0) {
            throw new ArithmeticException("Expressions.Modulo 0 in: " + e1 + e2);
        }
        Calculate c2 = new Calculate(e2, parentBlock);
        Calculate c1 = new Calculate(e1,parentBlock);
        return c1.run() % c2.run();
    }


    @Override
    public String toString() {return "(" + e1 + " % " +  e2 + ")";}
}
