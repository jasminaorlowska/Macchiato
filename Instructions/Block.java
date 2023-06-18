package Instructions;
import Exceptions.*;
import Expressions.Expression;
import Expressions.Variable;
import Macchiato.Debugger;

import java.util.*;

public class Block extends InstructionComplex {

    private  Variables variables;
    private Procedures procedures;

    public Block(Block.Builder<?> builder) {
        super(builder);
        initialize();
        for (Procedure p : builder.procedures) {
            procedures.addProcedure(p);
        }
        for (Variable v : builder.variables) {
            variables.addVariable(v);
        }
    }

    //constructor helper methods
    private void initialize() {
        initializeProceduresAndVariables();
        initializeVariablesInitialization(); //adding variables initialization at index 0 to the instruction list
        addBeginEndBlock(); //adding 'begin block' at the beggining of instructions and 'end block' at the end
    }
    private void initializeProceduresAndVariables () {
        this.procedures = new Procedures();
        this.variables = new Variables();
        variables.setParentBlock(this);
        procedures.setParentBlock(this);
    }
    private void initializeVariablesInitialization() {
        VariablesInitialization vInit = new VariablesInitialization(variables);
        getInstructions().add(0, vInit);
        vInit.setParentBlock(this);
        variables.linkVariablesInitialization(vInit);
    }
    private void addBeginEndBlock() {
        Instruction i = new BlockBeginEnd(false);
        addInstruction(i); //end block instruction
        i = new BlockBeginEnd(true);
        getInstructions().add(0, i); //begin block instruction
        i.setParentBlock(this);
    }

    //Getters of specific procedure/variable, returns null if it doesn't exist
    @Override public Procedure getProcedure(String name) {
        return procedures.getProcedure(name);
    }
    @Override public Variable getVariable(Variable variable) {
        return variables.getVariable(variable);
    }
    //Getters
    public Set<Procedure> getProcedures() {
        return procedures.getProcedures();
    }
    @Override public LinkedHashSet<Variable> getVariables() {
        return variables.getVariables();
    }

    public void run(Debugger d) throws EndOfStepsException, UndefinedVariableException {
        runInstructions(d);
        restartInstructions();
        setRun(true);
    }

    //------------BUILDER--------------//
    public static class Builder<T extends Block.Builder> extends InstructionComplex.Builder<T> {

        private final Set<Variable> variables;
        private final Set<Procedure> procedures;

        public Builder() {
            super();
            this.variables = new HashSet<>();
            this.procedures = new HashSet<>();
        }

        public T declareVariable(char name, Expression e) {
            variables.add(new Variable(name, e));
            return (T) this;
        }
        public T declareProcedure(String name, ArrayList<Character> arguments, Instruction... instructions) {
            ArrayList<Instruction> instructionsArray = new ArrayList<>();
            Collections.addAll(instructionsArray, instructions);
            procedures.add(new Procedure(name, new LinkedHashSet<>(arguments), instructionsArray));
            return (T) this;
        }

        public Block build() {
            return new Block(this);
        }
    }
}