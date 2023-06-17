package Builders;

import Expressions.Expression;
import Expressions.Variable;
import Instructions.ForLoop;

public class ForLoopBuilder extends InstructionComplexBuilder<ForLoop>{

    private final Variable variable;
    private final Expression expression;

    public Expression getExpression() {
        return expression;
    }
    public Variable getVariable() {
        return variable;
    }

    public ForLoopBuilder(Variable variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    public ForLoop build() {
        return new ForLoop(this);
    }
}
