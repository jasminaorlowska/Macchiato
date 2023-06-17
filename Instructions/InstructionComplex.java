package Instructions;
import Builders.InstructionComplexBuilder;
import Exceptions.*;
import Expressions.Variable;
import Macchiato.Debugger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**Instruction that can contain other instructions within it*/
public abstract class InstructionComplex extends Instruction{

    private final ArrayList<Instruction> instructions;
    private boolean startedRunning;

    public InstructionComplex() {
        super();
        this.instructions = new ArrayList<>();
        startedRunning = false;
    }

    public InstructionComplex(InstructionComplexBuilder<?> builder) {
        super();
        this.instructions = new ArrayList<>();
        startedRunning = false;
        for (Instruction i : builder.getInstructions()) {
            addInstruction(i);
        }
    }

    protected boolean startedRunning() {return startedRunning;}
    protected void setStartedRunning(boolean startedRunning) {
        this.startedRunning = startedRunning;
    }
    public Variable getVariable(Variable variable) {
        return this.getParentBlock().getVariable(variable);
    }
    public Procedure getProcedure(String name) {
        return this.getParentBlock().getProcedure(name);
    }
    public LinkedHashSet<Variable> getVariables() {
        return null;
    }
    public Set<Procedure> getProcedures() {
        return null;
    }
    public ArrayList<Instruction> getInstructions () {
        return instructions;
    }
    public void addInstruction(Instruction i) {
        if (i.isAdded()) {
            System.out.println("instruction is already added somewhere else");
            return;
        }
        this.instructions.add(i);
        i.setParentBlock(this);
    }

    @Override public void restart() {
        for (Instruction i : instructions) {
            i.restart();
        }
        startedRunning = false;
        setRun(false);
    }
    public void runInstructions(Debugger d) throws EndOfStepsException,
            UndefinedVariableException, ArithmeticException{
        for (Instruction i : instructions) {
            if (!i.isRun()) i.run(d);
        }
    }
}




