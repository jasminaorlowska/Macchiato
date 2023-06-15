package Instructions;
import Exceptions.*;
import Expressions.Expression;
import Expressions.Variable;
import Macchiato.Debugger;

import java.util.ArrayList;
import java.util.LinkedHashSet;
/**Invokes a procedure. Ensures that the arguments of invoking the procedure and procedure declaration
 * are correct. */
public class ProcedureInvoke extends InstructionComplex{
    private final String name;
    private final ArrayList<Expression> arguments;
    private Block parentBlock;
    private Variables variables;

    public ProcedureInvoke(String name, ArrayList<Expression> arguments) {
        super();
        if (!name.matches("[a-z]+")) {
            throw new IllegalArgumentException("Wrong procedure name. You can only use characters.");
        }
        this.name=name;
        this.arguments = arguments;
        variables = new Variables();
        variables.setParentBlock(this);
    }

    //Creates variables from given names (in procedure declaration) and arguments (in proedure invoke),
    //moves instructions (from procedure declaration) to get them invoked
    private void createVariablesMoveInstructions(LinkedHashSet<Character> vars, Procedure procedure) {
        VariablesInitialization vInit = new VariablesInitialization(this.variables);
        addInstruction(vInit);
        this.variables.linkVariablesInitialization(vInit);

        int i = 0;
        for (Character c : vars) {
            this.variables.addVariable(new Variable(c, arguments.get(i)));
            i++;
        }

        for (Instruction instruction : procedure.getInstructions()) {
            addInstruction(instruction);
        }
    }

    @Override
    public Variable getVariable(Variable variable) {
        return variables.getVariable(variable);
    }

    @Override
    public void run(Debugger d) throws EndOfStepsException, UndefinedVariableException, IllegalArgumentException {
        if (!startedRunning()) {
            setStartedRunning(true);
            Procedure procedure = getParentBlock().getProcedure(name);
            if (procedure == null) {
                System.out.println("Procedure doesn't exist");
                setRun(true);
                return;
            }
            LinkedHashSet<Character> variables = procedure.getVariables();
            if (arguments.size() != variables.size()) {
                setRun(true);
                throw new IllegalArgumentException("Too little arguments in the procedure " + procedure.getName());
            }
            createVariablesMoveInstructions(variables, procedure);
        }
        runInstructions(d);
        setRun(true);
    }
}
