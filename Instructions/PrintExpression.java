package Instructions;

import Exceptions.*;
import Expressions.Expression;
import Macchiato.Debugger;

public class PrintExpression extends Instruction {

    private final Calculate expression;

    public PrintExpression(Expression expression) {
        if (expression == null) {
            throw new IllegalArgumentException("Expressions.Expression can't be null");
        }
        this.expression = new Calculate(expression, this);
    }

    public void run(Debugger d) throws EndOfStepsException, UndefinedVariableException {
        if (d.getSteps() == 0) {
            throw new EndOfStepsException(this.toString());
        }

        d.changeSteps();
        setRun(true);

        try {
                int e = expression.run();
                System.out.println(e);
        } catch (ArithmeticException | UndefinedVariableException e) {
        }
    }

    public String toString() {
        return "print " + expression.toString();
    }
}
