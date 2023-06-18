package test;

import Builders.List;
import Expressions.Expression;
import Instructions.*;
import Instructions.Program;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**Instruction complex can contain multiple instructions within it
 * tests mostly check adding those instructions */
public class InstructionComplexBuilderTest {

    @Test
    public void invokeIncorrectNameProcedure() {
        assertThrows(IllegalArgumentException.class, () -> {
            Program.Builder b = new Program.Builder("p");
            b.invoke("aA", List.of(Expression.constant(1)));
            Program block = b.build();
        });
    }

    @Test void incorrectIf() {
        assertThrows(IllegalArgumentException.class, () -> {
            Program.Builder b = new Program.Builder("p");
            b.conditionalStatement(
                    new If.Builder(Expression.constant(1),
                                    "&",
                                Expression.constant(1)).
                    build());
            Program block = b.build();
        });
    }

    @Test void incorrectFor() {
        assertThrows(IllegalArgumentException.class, () -> {
            Program.Builder b = new Program.Builder("p");
            b.iteration(
                    new ForLoop.Builder(Expression.var('a'), null).
                            build());
            Program block = b.build();
        });
    }


}
