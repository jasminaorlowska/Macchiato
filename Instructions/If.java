package Instructions;

import Builders.IfBuilder;
import Exceptions.*;
import Expressions.Expression;
import Macchiato.Debugger;

public class If extends InstructionComplex{

    private final String operator;
    private final Calculate expression1;
    private final Calculate expression2;
    private boolean shouldExecute;

    public If(Expression e1, Expression e2, String operator){
        super();
        checkArguments(e1, e2, operator);
        this.operator = operator;
        this.expression1 = new Calculate(e1, this);
        this.expression2 = new Calculate(e2, this);
        this.shouldExecute = false;
    }

    public If(IfBuilder builder) {
        checkArguments(builder.getE1(), builder.getE2(), builder.getOperator());
        this.operator = builder.getOperator();
        this.expression1 = new Calculate(builder.getE1(), this);
        this.expression2 = new Calculate(builder.getE2(), this);
        this.shouldExecute = false;
        for (Instruction i : builder.getInstructions()) {
            addInstruction(i);
        }
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

        setRun(true);
        d.stackPop();
    }

    public String toString() {
        return "if " + expression1.toString() + " " + operator + " " + expression2.toString();
    }
}
