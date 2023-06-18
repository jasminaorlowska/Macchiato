package Instructions;
import Exceptions.*;
import Expressions.Variable;
import Macchiato.Debugger;
public abstract class Instruction {

    private boolean added;
    private InstructionComplex parentBlock;
    private boolean run;

    public Instruction() {
        added = false;
        run = false;
    }
    public abstract void run(Debugger d) throws EndOfStepsException, UndefinedVariableException, IllegalArgumentException;
    protected void setRun(boolean run) {
        this.run = run;
    }
    public boolean isRun() {return run;}

    protected InstructionComplex getParentBlock() {
        return parentBlock;
    }
    protected void setParentBlock(InstructionComplex parentBlock) {
        this.parentBlock = parentBlock;
    }
    public Variable getVariable(Variable variable) {
        return this.getParentBlock().getVariable(variable);
    }
    public void restart() {
            setRun(false);
        }
}
