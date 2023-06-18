package Instructions;

import Expressions.Variable;
import java.util.LinkedHashSet;

/**API of variables available in the block**/
public class Variables {

    private final LinkedHashSet<Variable> variables;
    private InstructionComplex parentBlock;
    private VariablesInitialization variablesInit;

    //Initialization
    public Variables() {
        this.variables = new LinkedHashSet<>();
        parentBlock = null;
    }
    public void setParentBlock(InstructionComplex parentBlock) {
        this.parentBlock = parentBlock;
    }
    public void linkVariablesInitialization(VariablesInitialization variablesInit) {
        this.variablesInit = variablesInit;
    }

    //Getters
    public Variable getVariable(Variable variable) {
        //Error handling - variables are not assigned to the block.
        if (parentBlock == null) {
            System.out.println("No parent. Error");
            return null;
        }
        for (Variable v : variables) {
            if (variable.equals(v)) return v;
        }
        //Variable is not present in the current block, check if the block has a parent, and if so, search there.
        if (parentBlock.getParentBlock() != null) return parentBlock.getParentBlock().getVariable(variable);
        return null;
    }
    public int getSize() {return variables.size();}
    public LinkedHashSet<Variable> getVariables() {return variables;}


    public void addVariable(Variable v) {
        if (!variables.add(v)) System.out.println("Variable '"+ v.getName() +"' already exists in the scope");
        else {
            //Adding instruction to initialization of variables
            variablesInit.addInstruction(new ChangeValueVariable(v, v.getExpression()));
        }
    }
    public void removeVariable(Variable v) {
        boolean res = variables.remove(v);
        if (!res) System.out.println("No such variable to remove");
        else {
            System.out.println("Variable successfully removed");
            //Removing instruction from initialization of Variables
            for (Instruction i : variablesInit.getInstructions()) {
                ChangeValueVariable initialization = (ChangeValueVariable) i;
                        if (initialization.getVariable() == v) {
                            variablesInit.getInstructions().remove(i);
                        }
            }
        }
    }

}
