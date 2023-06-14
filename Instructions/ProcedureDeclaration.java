package Instructions;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ProcedureDeclaration {

    public ProcedureDeclaration(String name, LinkedHashSet<Character> variables, ArrayList<Instruction> instructions, Block parentBlock)
    throws IllegalArgumentException{
        if (!name.matches("[a+z]+")) {
            throw new IllegalArgumentException("Wrong procedure name. You can only use characters.");
        }
        Procedure p = new Procedure(name, variables, instructions);
        parentBlock.addProcedure(p);
    }


}
