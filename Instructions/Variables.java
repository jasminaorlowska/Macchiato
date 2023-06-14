package Instructions;

import Expressions.Variable;
import java.util.HashSet;
import java.util.Set;

/**API of variables available in the block**/
public class Variables {

    private Set<Variable> variables;
    private InstructionComplex parentBlock;
    public Variables() {
        this.variables = new HashSet<>();
        parentBlock = null;
    }

    public void setParentBlock(InstructionComplex parentBlock) {
        this.parentBlock = parentBlock;
    }
    public void addVariable(Variable v) {
        variables.add(v);
    }
    public void removeVariable(Variable v) {
        boolean res = variables.remove(v);
        if (!res) System.out.println("No such variable to remove");
        else System.out.println("variable successfully removed");
    }

    public Variable getVariable(Variable variable) {
        //Error handling - variables are not assigned to the block.
        if (parentBlock == null) {
            System.out.println("No parent. Error");
            return null;
        }
        for (Variable v : variables) {
            if (variable.equals(v)) return v;
        }
        //The variable is not present in the current block, check if the block has a parent, and if so, search there.
        if (parentBlock.getParentBlock() != null) return parentBlock.getParentBlock().getVariable(variable);
        return null;
    }

    public int getSize() {return variables.size();}
    public Set<Variable> getVariables() {return variables;}
}
