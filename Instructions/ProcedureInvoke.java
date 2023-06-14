package Instructions;

import Exceptions.*;
import Expressions.Expression;
import Expressions.Variable;
import Macchiato.Debugger;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ProcedureInvoke extends InstructionComplex{
    private final String name;
    private final ArrayList<Expression> arguments;
    private final Block parentBlock;
    private Variables variables;

    public ProcedureInvoke(String name, ArrayList<Expression> arguments, Block parentBlock) {
        super();
        if (!name.matches("[a+z]+")) {
            throw new IllegalArgumentException("Wrong procedure name. You can only use characters.");
        }
        this.name=name;
        this.arguments = arguments;
        this.parentBlock = parentBlock;
        variables = new Variables();
        variables.setParentBlock(this);
    }

    @Override
    public Variable getVariable(Variable variable) {
        return variables.getVariable(variable);
    }

    @Override
    public void run(Debugger d) throws EndOfStepsException, UndefinedVariableException, IllegalArgumentException {
        if (!startedRunning()) {
            setStartedRunning(true);
            Procedure p = parentBlock.getProcedure(name);
            if (p == null) {
                System.out.println("Procedure doesn't exist");
                setRun(true);
                return;
            }
            LinkedHashSet<Character> variables = p.getVariables();
            if (arguments.size() != variables.size()) {
                setRun(true);
                throw new IllegalArgumentException("Too little arguments in the procedure " + p.getName());
            }
            Variables vars = new Variables();
            int i = 0;
            for (Character c : variables) {
                vars.addVariable(new Variable(c, arguments.get(i)));
                i++;
            }
            addInstruction(new InitializationOfVariables(vars));
            for (Instruction instruction : getInstructions()) instruction.setParentBlock(this);
        }
        runInstructions(d);
        setRun(true);
    }
}
