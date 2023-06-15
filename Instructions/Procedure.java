package Instructions;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;

public class Procedure {
    private LinkedHashSet<Character> variables;
    private String name;
    private ArrayList<Instruction> instructions;

    public Procedure(String name, LinkedHashSet<Character> variables, ArrayList<Instruction> instructions) {
        this.name = name;
        this.variables = variables;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public LinkedHashSet<Character> getVariables() {
        return variables;
    }
    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Procedure procedure = (Procedure) obj;
        return name.equals(procedure.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
