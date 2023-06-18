package Instructions;

import Exceptions.*;
import Macchiato.Debugger;

public class Program extends Block {

    private String name;
    public String getName() {
        return name;
    }
    public void changeName(String name) { this.name = name; }

    public Program(Program.Builder builder) {
        super(builder);
        this.setParentBlock(null);
        this.name = builder.name;
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
        restartInstructions();
//        setRun(true);
    }

    //------------BUILDER--------------//
    public static class Builder extends Block.Builder<Builder> {

        private final String name;

        public Builder(String name) {
            super();
            this.name = name;
        }

        public Program build() {return new Program(this);}

    }
}
