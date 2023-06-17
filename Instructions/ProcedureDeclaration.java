package Instructions;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;

/**Declares a procedure. Procedure outline. Can't be invoked*/
public class ProcedureDeclaration {

    private final String name;
    private final LinkedHashSet<Character> variables;
    private final ArrayList<Instruction> instructions;
    public ProcedureDeclaration(String name, LinkedHashSet<Character> variables,
                                ArrayList<Instruction> instructions)
    throws IllegalArgumentException {
        if (!name.matches("[a-z]+")) {
            throw new IllegalArgumentException("Wrong procedure name. You can only use characters.");
        }
        this.name=name;
        this.variables=variables;
        this.instructions=instructions;
    }

    //Getters
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
        ProcedureDeclaration procedure = (ProcedureDeclaration) obj;
        return getName().equals(procedure.getName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
