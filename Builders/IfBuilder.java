package Builders;

import Expressions.Expression;
import Instructions.If;

public class IfBuilder extends InstructionComplexBuilder<If>{

    private final Expression e1;
    private final String operator;
    private final Expression e2;

    public Expression getE1() {return e1;}
    public Expression getE2() {return e2;}
    public String getOperator() {return operator;}

    public IfBuilder(Expression e1, String operator, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    public If build() {
        return new If(this);
    }
}
