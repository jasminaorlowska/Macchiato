package Instructions;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;

/**Procedure outline. Can't be invoked*/
public class Procedure extends ProcedureDeclaration{

    public Procedure(String name, LinkedHashSet<Character> variables, ArrayList<Instruction> instructions) {
        super(name, variables, instructions);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Procedure procedure = (Procedure) obj;
        return getName().equals(procedure.getName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
