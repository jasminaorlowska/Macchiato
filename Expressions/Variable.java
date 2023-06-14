package Expressions;

import Exceptions.UndefinedVariableException;
import Instructions.Instruction;

public class Variable implements Expression{

    private final Expression expression;
    private final char name;
    private int value;

    public Variable(char name, Expression expression) throws IllegalArgumentException{
        if (!Character.isLetter(name)) {
            String message = "Wrong name of the variable. You can only use letters from a to z.";
            throw new IllegalArgumentException(message);
        }
        if (expression == null) {
            throw new IllegalArgumentException("Expressions.Expression can't be null");
        }
        else {
            this.name = name;
            this.expression = expression;
            this.value = 0;
        }
    }
    public Variable(char name) throws IllegalArgumentException{
        if (!Character.isLetter(name)) {
            String message = "Wrong name of the variable. You can only use letters from a to z.";
            throw new IllegalArgumentException(message);
        }
        else {
            this.name = name;
            this.expression = new IntegerLiteral(0);
            this.value = 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Variable variable = (Variable) obj;
        return this.name == variable.getName();
    }
    public int hashCode() {
        return Character.hashCode(name);
    }
    public char getName() {
        return this.name;
    }
    public Expression getExpression() {return expression;}
    public void setValue(int value) {this.value = value;}
    public int getValue() {return value;}
    public int calculate() throws ArithmeticException {return value;}
    public int calculate(Instruction parentBlock) throws UndefinedVariableException {
        if (parentBlock.getVariable(this) == null) {
            throw new UndefinedVariableException(this.name);
        }
        return value;
    }
    public String toString() {
        return Character.toString(name);
    }
}
