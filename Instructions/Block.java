package Instructions;
import Exceptions.*;
import Expressions.Variable;
import Macchiato.Debugger;

import java.util.LinkedHashSet;
import java.util.Set;
public class Block extends InstructionComplex {

    private Variables variables;
    private Procedures procedures;
    public Block(Variables variables) {
        super();
        this.variables = variables;
        this.procedures = new Procedures();
        variables.setParentBlock(this);
        Instruction i = new BlockBeginEnd(false); //end block instruction
        getInstructions().add(i);
        i.setParentBlock(this);
        addInstruction(new BlockBeginEnd(true)); //begin block instruction
        addInstruction(new InitializationOfVariables(variables));
    }
    public Block() {
        super();
        this.variables = new Variables();
        this.procedures = new Procedures();
        Instruction i = new BlockBeginEnd(false);
        getInstructions().add(i); //end block instruction
        i.setParentBlock(this);
        addInstruction(new BlockBeginEnd(true)); //begin block instruction
        addInstruction(new InitializationOfVariables(variables));
    }
    public void addProcedure(Procedure p) {
        procedures.addProcedure(p);
    }

    @Override public void addInstruction(Instruction i) {
        if (i.isAdded()) {
            System.out.println("instruction is already added somewhere else");
            return;
        }
        //second to last position because we want the last instruction to be end block.
        getInstructions().add(getInstructions().size() - 1, i);
        i.setParentBlock(this);
    }
    @Override
    public Procedure getProcedure(String name) {
        return procedures.getProcedure(name);
    }

    @Override public Variable getVariable(Variable variable) {
        return variables.getVariable(variable);
    }
    public void run(Debugger d) throws EndOfStepsException, UndefinedVariableException {
        runInstructions(d);
    }

    @Override public LinkedHashSet<Variable> getVariables() {
        return variables.getVariables();
    }

}