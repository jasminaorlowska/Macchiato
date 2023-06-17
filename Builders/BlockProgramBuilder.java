package Builders;

import Expressions.Expression;
import Expressions.Variable;
import Instructions.Block;
import Instructions.Instruction;
import Instructions.Procedure;

import java.util.*;

public abstract class BlockProgramBuilder<S extends Block> extends InstructionComplexBuilder<S> {

    private final Set<Variable> variables;
    private final Set<Procedure> procedures;

    public BlockProgramBuilder() {
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

    public BlockProgramBuilder<S> declareVariable(char name, Expression e) {
        variables.add(new Variable(name, e));
        return this;
    }
    public BlockProgramBuilder<S> declareProcedure(String name, ArrayList<Character> arguments, Instruction... instructions) {
        ArrayList<Instruction> instructionsArray = new ArrayList<>();
        Collections.addAll(instructionsArray, instructions);
        procedures.add(new Procedure(name, new LinkedHashSet<>(arguments), instructionsArray));
        return this;
    }
}
