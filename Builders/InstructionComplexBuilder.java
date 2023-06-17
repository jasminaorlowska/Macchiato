package Builders;

import Expressions.Expression;
import Expressions.Variable;
import Instructions.*;
import java.util.ArrayList;

public abstract class InstructionComplexBuilder<T extends InstructionComplex> {

    private final ArrayList<Instruction> instructions;
    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public InstructionComplexBuilder () {
        this.instructions = new ArrayList<>();
    }
    public InstructionComplexBuilder assign(char name, Expression e) {
        instructions.add(new ChangeValueVariable(new Variable(name), e));
        return this;
    }
    public InstructionComplexBuilder invoke(String name, ArrayList<Expression> arguments) {
        instructions.add(new ProcedureInvoke(name, arguments));
        return this;
    }
    public InstructionComplexBuilder block(Block b) {
        instructions.add(b);
        return this;
    }
    public InstructionComplexBuilder iteration(ForLoop f) {
        instructions.add(f);
        return this;
    }
    public InstructionComplexBuilder conditionalStatement(If ifStatement) {
        instructions.add(ifStatement);
        return this;
    }

    public InstructionComplexBuilder print(Expression e) {
        instructions.add(new PrintExpression(e));
        return this;
    }
    public abstract T build();
}
