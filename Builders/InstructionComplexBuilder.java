package Builders;

import Expressions.Expression;
import Expressions.Variable;
import Instructions.*;
import java.util.ArrayList;

public abstract class InstructionComplexBuilder <T extends InstructionComplexBuilder> {

    protected final ArrayList<Instruction> instructions;

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public InstructionComplexBuilder () {
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
    public abstract InstructionComplex build() ;
}
