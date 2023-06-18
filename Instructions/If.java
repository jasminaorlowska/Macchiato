package Instructions;

import Exceptions.*;
import Expressions.Expression;
import Macchiato.Debugger;

public class If extends InstructionComplex{

    private final String operator;
    private final Calculate expression1;
    private final Calculate expression2;
    private boolean shouldExecute;

    public If(If.Builder builder) {
        super(builder);
        checkArguments(builder.e1, builder.e2, builder.operator);
        this.operator = builder.operator;
        this.expression1 = new Calculate(builder.e1, this);
        this.expression2 = new Calculate(builder.e2, this);
        this.shouldExecute = false;
    }

    private void checkArguments(Expression e1, Expression e2, String operator) {
        if (!operator.matches("^(=|<>|<|>|<=|>=)$")) {
            String message = "Wrong operator, you can only use: '=', '<>', '<', '>', '<=', '>='";
            throw new IllegalArgumentException(message);
        }
        if (e1 == null || e2 == null) {
            throw new IllegalArgumentException("Expressions can't be null");
        }
    }
    private boolean calculateResult() throws ArithmeticException, UndefinedVariableException {
        try {
             int e1 = expression1.run();
             int e2 = expression2.run();
            boolean result;
            switch (operator) {
                case "=" -> result = e1 == e2;
                case "<>" -> result = e1 != e2;
                case "<" -> result = e1 < e2;
                case ">" -> result = e1 > e2;
                case "<=" -> result = e1 <= e2;
                case ">=" -> result = e1 >= e2;
                default -> {
                    return false;
                }
            }
            return result;
        } catch (ArithmeticException e) {
            throw new ArithmeticException(this.toString());
        }
    }

    //Instrukcje do oblsluzenia debuggera.
    public void run(Debugger d) throws EndOfStepsException, ArithmeticException, UndefinedVariableException{
        if (!startedRunning()) {
            if (d.getSteps() == 0) {
                throw new EndOfStepsException(this.toString());
            }

            d.stackPush(this);
            d.changeSteps();
            setStartedRunning(true);

            try {
                shouldExecute = calculateResult();
            } catch (ArithmeticException | UndefinedVariableException e) {
                setRun(true);
                d.stackPop();
                throw e;
            }
        }

        if (shouldExecute) {
            runInstructions(d);
        }

        restartInstructions();
        setRun(true);
        d.stackPop();
    }

    public String toString() {
        return "if " + expression1.toString() + " " + operator + " " + expression2.toString();
    }

    //------------BUILDER--------------//
    public static class Builder extends InstructionComplex.Builder<Builder> {

        private final Expression e1;
        private final String operator;
        private final Expression e2;

        public Builder(Expression e1, String operator, Expression e2) {
            super();
            this.e1 = e1;
            this.e2 = e2;
            this.operator = operator;
        }

        public If build() {
            return new If(this);
        }
    }

}
