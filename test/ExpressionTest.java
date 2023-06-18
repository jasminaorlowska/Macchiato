package test;

import Exceptions.*;
import Expressions.Expression;
import Instructions.Program;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**Mogłabym więcej testów do expression wiem, ale z grubsza idea chyba ok */
class ExpressionTest {

    //mainly to test variable not found
    Program p = new Program.Builder("p").build();

    //Basic operations check
    @Test
    public void twoPlusThreeIsTwo() throws UndefinedVariableException {
        int v = Expression.sum(Expression.constant(2), Expression.constant(3)).calculate(p);
        assertEquals(v, 5);
    }
    @Test
    public void twoMultiplyZeroIsZero() throws UndefinedVariableException {
        int v = Expression.multiply(Expression.constant(2), Expression.constant(0)).calculate(p);
        assertEquals(v, 0);
    }
    @Test
    public void fiveMinusThreeIsTwo() throws UndefinedVariableException {
        int v = Expression.subtract(Expression.constant(5), Expression.constant(3)).calculate(p);
        assertEquals(v, 2);
    }

    //Division/Modulo zero check
    @Test
    public void divideZeroThrowsException() {
        assertThrows(ArithmeticException.class, () -> {
            Expression.divide(Expression.constant(3), Expression.constant(0)).calculate(p);
        });
    }
    @Test
    public void moduloZeroThrowsException() {
        assertThrows(ArithmeticException.class, () -> {
            Expression.modulo(Expression.constant(3), Expression.constant(0)).calculate(p);
        });
    }

    //Variable undefined check
    @Test
    public void variableNotFound() {
        assertThrows(UndefinedVariableException.class, () -> {
            Expression.divide(Expression.constant(3), Expression.var('a')).calculate(p);
        });
    }

    //Wrong arguments check (for one expression because they all have same method
    // checkarguments that checks if they are ok)
    @Test
    public void checkNullExpression1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Expression.divide(Expression.constant(3), null).calculate(p);
        });
    }
    @Test
    public void checkNullExpression2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Expression.divide(null, Expression.constant(3)).calculate(p);
        });
    }
    @Test
    public void checkNullExpression3() {
        assertThrows(IllegalArgumentException.class, () -> {
            Expression.divide(null, null).calculate(p);
        });
    }

}