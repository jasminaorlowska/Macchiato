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
    public abstract void run(Debugger d) throws EndOfStepsException, UndefinedVariableException;
    protected void setAdded() {
        if (this.added == false) return;
        this.added = false;
    }
    public boolean isAdded() {
        return added;
    }
    protected void setRun(boolean run) {
        this.run = run;
    }
    public boolean isRun() {return run;}
    protected InstructionComplex getParentBlock() {
        return parentBlock;
    }
    protected void setParentBlock(InstructionComplex parentBlock) {
        if (!added) this.parentBlock = parentBlock;
        setAdded();
    }
    public Variable getVariable(Variable variable) {
        return this.getParentBlock().getVariable(variable);
    }
    public void restart() {
            setRun(false);
        }
}
