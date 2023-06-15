package Instructions;
import Exceptions.*;
import Expressions.Variable;
import Macchiato.Debugger;
import java.util.LinkedHashSet;
public class Block extends InstructionComplex {

    private final Variables variables;
    private Procedures procedures;

    public Block(Variables variables) {
        super();
        this.variables = variables;
        initialize();
    }
    public Block() {
        super();
        this.variables = new Variables();
        initialize();
    }

    //constructor helper methods
    private void initialize() {
        initializeProceduresAndVariables();
        addBeginEndBlock();
        initializeVariablesInitialization();
    }
    private void initializeProceduresAndVariables () {
        this.procedures = new Procedures();
        variables.setParentBlock(this);
        procedures.setParentBlock(this);
    }
    private void initializeVariablesInitialization() {
        VariablesInitialization vInit = new VariablesInitialization(variables);
        addInstruction(vInit);
        variables.linkVariablesInitialization(vInit);
    }
    private void addBeginEndBlock() {
        Instruction i = new BlockBeginEnd(false);
        getInstructions().add(i); //end block instruction
        i.setParentBlock(this);
        addInstruction(new BlockBeginEnd(true)); //begin block instruction
    }

    //Adding variables and procedure declarations
    public void addProcedureDeclaration(ProcedureDeclaration procedureDeclaration) {
        procedures.addProcedure(procedureDeclaration.createProcedure());
    }
    public void addVariable(Variable v) {
        variables.addVariable(v);
    }

    //Getters of specific procedure/variable
    @Override public Procedure getProcedure(String name) {
        return procedures.getProcedure(name);
    }
    @Override public Variable getVariable(Variable variable) {
        return variables.getVariable(variable);
    }

    @Override public void addInstruction(Instruction i) {
        if (i.isAdded()) {
            System.out.println("Instruction is already added somewhere else");
            return;
        }
        //second to last position because we want the last instruction to be end block.
        getInstructions().add(getInstructions().size() - 1, i);
        i.setParentBlock(this);
    }
    public void run(Debugger d) throws EndOfStepsException, UndefinedVariableException {
        runInstructions(d);
    }

    @Override public LinkedHashSet<Variable> getVariables() {
        return variables.getVariables();
    }
}