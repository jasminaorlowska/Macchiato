package Builders;
import Expressions.Expression;
import Expressions.Variable;
import Instructions.*;
import java.util.*;


public class BlockBuilder<T extends BlockBuilder> extends InstructionComplexBuilder<T> {

    private final Set<Variable> variables;
    private final Set<Procedure> procedures;

    public BlockBuilder() {
        super();
        this.variables = new HashSet<>();
        this.procedures = new HashSet<>();
    }

    public Set<Procedure> getProcedures() {
        return procedures;
    }
    public Set<Variable> getVariables() {
        return variables;
    }

    public T declareVariable(char name, Expression e) {
        variables.add(new Variable(name, e));
        return (T) this;
    }
    public T declareProcedure(String name, ArrayList<Character> arguments, Instruction... instructions) {
        ArrayList<Instruction> instructionsArray = new ArrayList<>();
        Collections.addAll(instructionsArray, instructions);
        procedures.add(new Procedure(name, new LinkedHashSet<>(arguments), instructionsArray));
        return (T) this;
    }

    public Block build() {
        return new Block(this);
    }

}
