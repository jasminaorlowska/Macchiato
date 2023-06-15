package Instructions;

import Exceptions.*;
import Macchiato.Debugger;

public class Program extends Block {

    private String name;
    public Program(String name, Variables variables) {
        super(variables);
        this.setParentBlock(null);
        this.name = name;
    }
    public Program(String name) {
        super();
        this.setParentBlock(null);
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void changeName(String name) {
        this.name = name;
    }

    public void run(Debugger d) throws EndOfStepsException, ArithmeticException, UndefinedVariableException {
        InstructionComplex last = d.getLastInstruction();
        if (last == this) { //first
            runInstructions(d);
        }
        else if (last == null && d.isRunning()) {
            runInstructions(d);
        }
        else {
            while (last != null) {
                last.run(d);
                last = d.getLastInstruction();
            }
        }
        setRun(true);
    }

}
