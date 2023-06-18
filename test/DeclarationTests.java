package test;
import Builders.List;
import Expressions.Expression;
import Instructions.Block;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class DeclarationTests {

    //Double declarations tests
    @Test void doubleVariableDeclaration() {
        Block.Builder b = new Block.Builder();
        b.declareVariable('a', Expression.constant(1));
        b.declareVariable('a', Expression.constant(2));
        Block block = b.build();
        assertEquals(block.getVariables().size(), 1);
    }

    @Test void doubleProcedureDeclaration() {
        Block.Builder b = new Block.Builder();
        b.declareProcedure("zajeprocedura", List.of('a','b'));
        b.declareProcedure("zajeprocedura", List.of('c','d'));
        Block block = b.build();
        assertEquals(block.getProcedures().size(), 1);
    }

    //Wrong name tests
    @Test void wrongNameProcedure() {
        assertThrows(IllegalArgumentException.class, () -> {
            Block.Builder b = new Block.Builder();
            b.declareProcedure("procka123", List.of('a','b'));
            Block block = b.build();
        });
    }

}
