package Instructions;
import Exceptions.*;
import Expressions.Expression;
import Expressions.Variable;
import Macchiato.Debugger;

public class ChangeValueVariable extends Instruction {

    private final Variable variable;
    private final Calculate expression;

    public ChangeValueVariable(Variable v, Expression newValue){
        super();
        if (v == null || newValue == null) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        this.variable = v;
        this.expression = new Calculate(newValue, this);
    }

    public Variable getVariable() {return variable;}

    public void run(Debugger d) throws EndOfStepsException, ArithmeticException, UndefinedVariableException {
        if (isRun()) return;
        if (d.getSteps() == 0) {
            throw new EndOfStepsException(this.toString());
        }

        setRun(true);
        d.changeSteps();

        try {
            if (getParentBlock().getVariable(variable) == null) {
                throw new UndefinedVariableException(variable.getName());
            }
            int newValue = expression.run();
            variable.setValue(newValue);
        } catch (ArithmeticException e) {
            throw new ArithmeticException(this.toString());
        }
    }

    @Override public void restart() {
        setRun(false);
    }
    public String toString() {
        return variable.getName() + " = " + expression.toString();
    }
}

