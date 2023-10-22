package Instructions;
import Exceptions.*;
import Expressions.Expression;
import Expressions.Variable;
import Macchiato.Debugger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**Instruction that can contain other instructions within it*/
public abstract class InstructionComplex extends Instruction{

    private final ArrayList<Instruction> instructions;
    private boolean startedRunning;

    //Constructors
    public InstructionComplex(InstructionComplex.Builder<?> builder) {
        super();
        this.instructions = new ArrayList<>();
        startedRunning = false;
        for (Instruction i : builder.instructions) {
            addInstruction(i);
        }
    }
    public InstructionComplex() {
        super();
        this.instructions = new ArrayList<>();
        startedRunning = false;
    }

    //Getters
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

    //add instruction to instruction complex
    public void addInstruction(Instruction i) {
        instructions.add(i);
        i.setParentBlock(this);
    }

    //Restarts
    @Override public void restart() {
        startedRunning = false;
        setRun(false);
    }
    public void restartInstructions() {
        for (Instruction i : instructions) {
            i.restart();
        }
    }

    //invoking
    public void runInstructions(Debugger d) throws EndOfStepsException,
            UndefinedVariableException, ArithmeticException{
        for (Instruction i : instructions) {
            if (!i.isRun()) i.run(d);
        }
    }


    //------------BUILDER--------------//
    public static abstract class Builder <T extends InstructionComplex.Builder> {

        protected final ArrayList<Instruction> instructions;

        public Builder () {
            this.instructions = new ArrayList<>();
        }

        public T assign(char name, Expression e) {
            instructions.add(new ChangeValueVariable(new Variable(name), e));
            return (T) this;
        }
        public T invoke(String name, ArrayList<Expression> arguments) {
            instructions.add(new ProcedureInvoke(name, arguments));
            return (T) this;
        }
        public T block(Block b) {
            instructions.add(b);
            return (T) this;
        }
        public T iteration(ForLoop f) {
            instructions.add(f);
            return (T) this;
        }
        public T conditionalStatement(If ifStatement) {
            instructions.add(ifStatement);
            return (T) this;
        }

        public T print(Expression e) {
            instructions.add(new PrintExpression(e));
            return (T) this;
        }
        public abstract InstructionComplex build();
    }
}




