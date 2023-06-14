package Instructions;

import Expressions.Variable;

import java.util.Set;

public class Procedures {
    private Set<Procedure> procedures;
    private Block parentBlock;
    public void addProcedure(Procedure p) {
        if (!procedures.add(p)) System.out.println("Procedure already exists");
    }

    public void setParentBlock(Block parentBlock) {
        this.parentBlock = parentBlock;
    }

    public Procedure getProcedure(String name) {
        //Error handling - procedures are not assigned to the block.
        if (parentBlock == null) {
            System.out.println("No parent. Error");
            return null;
        }
        for (Procedure p : procedures) {
            if (p.getName().equals(name)) return p;
        }
        //Procedure is not present in the current block, check if the block has a parent, and if so, search there.
        if (parentBlock.getParentBlock() != null) return parentBlock.getParentBlock().getProcedure(name);
        return null;
    }
}
