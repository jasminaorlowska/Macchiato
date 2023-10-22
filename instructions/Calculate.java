package Instructions;

import Exceptions.UndefinedVariableException;
import Expressions.Expression;

public class Calculate {

    private final Expression expression;
    private final Instruction parentBlock;

    public Calculate(Expression expression, Instruction parentBlock) {
        if (expression == null) {
            throw new IllegalArgumentException("Expressions.Expression can't be null");
        }
        this.parentBlock = parentBlock;
        this.expression = expression;
    }

    public int run() throws UndefinedVariableException, ArithmeticException {
        return expression.calculate(parentBlock);
    }

    public String toString() {
        return expression.toString();
    }
}
