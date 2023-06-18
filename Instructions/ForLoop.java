package Instructions;

import Exceptions.*;
import Expressions.Expression;
import Expressions.IntegerLiteral;
import Expressions.Variable;
import Macchiato.Debugger;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ForLoop extends InstructionComplex{

    private final Calculate expression;
    private final Variable variable;
    private ArrayList<Instruction> helperInstructions;
    private int value;
    private int firstNotRun;

    public ForLoop(ForLoop.Builder builder) {
        super(builder);
        Variable variable = builder.variable;
        Expression expression = builder.expression;
        if (variable == null || expression == null) {
            throw new IllegalArgumentException("Arguments can't be null");
        }

        this.expression = new Calculate(expression, this);
        this.variable = variable;
        this.helperInstructions = new ArrayList<>();
        this.value = 0;
        this.firstNotRun = 0;
    }

    //Getters
    @Override public Variable getVariable(Variable variable) {
        if (this.variable.equals(variable)) return this.variable;
        return this.getParentBlock().getVariable(variable);
    }
    @Override public LinkedHashSet<Variable>  getVariables() {
        LinkedHashSet<Variable> variables = new LinkedHashSet<>();
        variables.add(variable);
        return variables;
    }

    //Invoke
    private void moveFromLists(ArrayList<Instruction> moveFrom, ArrayList<Instruction> moveTo) {
        moveTo.clear();
        moveTo.addAll(moveFrom);
        moveFrom.clear();
    }
    @Override public void runInstructions(Debugger d) throws EndOfStepsException, UndefinedVariableException, ArithmeticException{
        int iterator = 0;
        for (Instruction i : getInstructions()) {
            if (iterator >= firstNotRun) {
                try {
                    firstNotRun++; //Which instruction was the last one not done.
                    i.run(d);
                    i.restart(); //We need to set run=false because otherwise all instructions
                                // will be executed after one loop iteration.
                } catch (EndOfStepsException e) {
                    firstNotRun--;
                    throw e;
                }
            }
            iterator++;
        }

    }
    private void runLoop(Debugger d, int value) throws EndOfStepsException, UndefinedVariableException, ArithmeticException{
        moveFromLists(getInstructions(), helperInstructions); // During processing for loop,
                                                            // we replace the loop with a new list of instructions.

        //In the new list, all instructions are duplicated value times.
        for (int i = 0; i < value; i++) { //tworzymy bloki
            addInstruction(new ChangeValueVariable(variable, new IntegerLiteral(i)));
            for (Instruction instruction : helperInstructions) {
                addInstruction(instruction);
            }
        }

        runInstructions(d); //iterations through blocks
        moveFromLists(helperInstructions, getInstructions());
        helperInstructions.clear();
    }
    public void run(Debugger d) throws EndOfStepsException, UndefinedVariableException, ArithmeticException {
        if (!startedRunning()) {
            if (d.getSteps() == 0) {
                throw new EndOfStepsException(this.toString());
            }

            setStartedRunning(true);
            d.stackPush(this);
            d.changeSteps();

            try {
                value = expression.run();
            } catch (ArithmeticException e) {
                setRun(true);
                d.stackPop();
                throw new ArithmeticException(this.toString());
            } catch (UndefinedVariableException e) {
                setRun(true);
                d.stackPop();
                throw e;
            }

            runLoop(d, value);
        }
        else {
            runInstructions(d);
            moveFromLists(helperInstructions, getInstructions());
        }

        restartInstructions();
        setRun(true);
        d.stackPop();
    }

    public String toString() {
        return "for " + variable + " " + expression.toString();
    }


    //------------BUILDER--------------//
    public static class Builder extends InstructionComplex.Builder<Builder> {

        private final Variable variable;
        private final Expression expression;

        public Expression getExpression() {
            return expression;
        }
        public Variable getVariable() {
            return variable;
        }

        public Builder(Variable variable, Expression expression) {
            super();
            this.variable = variable;
            this.expression = expression;
        }
        public ForLoop build() {
            return new ForLoop(this);
        }
    }
}
