package Instructions;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**Declares a procedure. Contains a method that creates a procedure from declaration*/
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

    public Procedure createProcedure() {
        return new Procedure(name, variables, instructions);
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
}
