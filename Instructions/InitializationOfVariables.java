package Instructions;

import Exceptions.*;
import Expressions.Variable;
import Macchiato.Debugger;

public class InitializationOfVariables extends InstructionComplex{

    private Variables variables;

    public InitializationOfVariables(Variables variables) {
        super();
        this.variables = variables;
        for (Variable v : variables.getVariables()) {
            addInstruction(new ChangeValueVariable(v, v.getExpression()));
        }
    }

    public void run(Debugger d) throws EndOfStepsException {
        if (!startedRunning()) {
            d.stackPush(this);
            setStartedRunning(true);
        }

        for (Instruction i : getInstructions()) {
            if (!i.isRun()) {
                try {
                    i.run(d);
                } catch (ArithmeticException e) {
                    i.setRun(true);
                    ChangeValueVariable instruction = (ChangeValueVariable) i;
                    variables.removeVariable(instruction.getVariable());
                } catch (UndefinedVariableException e) {
                    //randomowy komentarz pozdrawiam
                }
            }

        }

        setRun(true);
        d.stackPop();
    }

    @Override
    public String toString() {
        return "initializing " + variables.getSize() + " variables ";
    }
}
