package Macchiato;

import Exceptions.*;
import Instructions.InstructionComplex;
import Instructions.Procedure;
import Instructions.Program;
import Expressions.Variable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Debugger {

    private boolean running;
    private int steps;
    private final ArrayList<InstructionComplex> stack;
    private final Program program;

    public Debugger(Program p) {
        this.steps = 0;
        this.stack = new ArrayList<>();
        this.program = p;
    }

    public int getSteps() {
        return steps;
    }
    public int getSize() {return stack.size();}
    public void changeSteps() {steps -= 1;}

    public void setSteps(int steps) {
        this.steps = steps;
    }
    public void stackPush(InstructionComplex i) {
        stack.add(i);
    }
    public void stackPop() {
        if (stack.size() == 0) {
            System.out.println("Stack empty, error");
            return;
        }
        stack.remove(stack.size() - 1);
    }

    public boolean isRunning() {
        return running;
    }

    private Set<Variable> getVariables(int level) {
        Set<Variable> vars = new HashSet<>();
        if (level > getSize()) {
            System.out.println("There is no such level");
            return vars;
        }
        else if (stack.size() == 0) {
            vars.addAll(program.getVariables());
        }
        else {
            for (int i = stack.size() - level - 1; i >= 0; i--) {
                Set<Variable> blockVars = stack.get(i).getVariables();
                if (blockVars != null) {
                    vars.addAll(blockVars);
                }
            }
        }
        return vars;
    }
    private Set<Procedure> getProcedures(int level) {
        Set<Procedure> procedures = new HashSet<>();
        if (stack.size() == 0) {
            procedures.addAll(program.getProcedures());
        }
        else {
            for (int i = stack.size() - level - 1; i >= 0; i--) {
                Set<Procedure> blockProcedures = stack.get(i).getProcedures();
                if (blockProcedures != null) {
                    procedures.addAll(blockProcedures);
                }
            }
        }
        return procedures;
    }

    public void displayVariables(int level) {
        Set<Variable> vars = getVariables(level);
        for (Variable v : vars) {
            System.out.println(v + " " + v.getValue());
        }
    }

    public InstructionComplex getLastInstruction() {
        if (stack.size() == 0) return null;
        return stack.get(stack.size() - 1);
    }
    private boolean caseC() {
        String messageEnd = "Program executed.";
        if (getSize() == 0) {
            //jezeli program nie zostal jeszcze odpalony
            runWithout();
        }
        runWithout();
        System.out.println(messageEnd);
        return true;
    }
    private boolean caseS(String[] tokens) {
        String messageEnd = "Program executed.";
        try {
            int steps = Integer.parseInt(tokens[1]);
            setSteps(steps);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing argument for 's command");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Second argument must be a number.");
            return false;
        }
        try {
            program.run(this);
        } catch (ArithmeticException | UndefinedVariableException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } catch (EndOfStepsException e) {
            displayVariables(0);
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println(messageEnd);
        displayVariables(0);
        program.restart();
        return true;
    }
    private void caseD(String[] tokens) {
        try {
            int level = Integer.parseInt(tokens[1]);
            if (level < 0) {
                System.out.println("Level can't be a negative number");
            }
            else displayVariables(level);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing argument for 'd' command");
        } catch (NumberFormatException e) {
            System.out.println("Second argument must be a number.");
        }
    }
    private void caseM(String[] tokens) {
        String path = tokens[1];
        try {
            File file = new File(path);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
            writeToBuffer(bw);
            bw.close();
        } catch (IOException e) {
            System.out.println("error");
        }
    }
    //Helper functions in 'm' case. Writes to buffer currently visible variables and procedures.
    private void writeToBuffer(BufferedWriter bw) throws IOException{
        Set<Variable> v = getVariables(0);
        bw.write("Variables:\n");
        for (Variable var : v) bw.write(var.getName() + " " + var.getValue() + "\n");
        bw.flush();
        Set<Procedure> p = getProcedures(0);
        for (Procedure procedure : p) {
            bw.write("Procedure name: " + procedure.getName() + "\n");
            LinkedHashSet<Character> vars = procedure.getVariables();
            for (Character c : vars) bw.write(c + " ");
            bw.write("\n");
        }
    }

    private boolean instructionsCases(String cmd, String[] tokens) {
        switch (cmd) {
            case "c": return caseC();
            case "s": return caseS(tokens);
            case "d": caseD(tokens); break;
            case "e": return true;
            case "m": caseM(tokens); break;
            default: System.out.println("Wrong argument"); break;
        }
        return false;
    }

    public void runWith() {
        running = true;
        Scanner scanner = new Scanner(System.in);
        String commands = """
                Continue program execution: "c"
                Execute a specific number of steps: "s <number>"
                Display current variable values up to a certain level: "d <number>"
                Perform a memory dump of the program into a file <file_name>: "m <file_name>"
                Exit the debugger and stop program execution: "e\"""";
        System.out.println("Executing with debugging. Here are the commands:\n" + commands);
        boolean ended = false;

        while (!ended) {
            System.out.println("debugger> ");
            String command = scanner.nextLine();
            String[] tokens = command.split(" ");
            String cmd = tokens[0];

            if (tokens.length > 2) {
                System.out.println("Too many arguments.");
            }
            else {
                ended = instructionsCases(cmd, tokens);
            }
        }
        running = false;

    }
    public void runWithout() {
        running = true;
        System.out.println("Executing without debugging.");
        setSteps(Integer.MAX_VALUE);

        while (running) {
            try {
                if (getSize() == 0) {
                    program.runInstructions(this);
                }
                else program.run(this);
                running = false;

            } catch (ArithmeticException | UndefinedVariableException e) {
                System.out.println("Error: " + e.getMessage());
                running = false;
            } catch (EndOfStepsException e) {
                setSteps(Integer.MAX_VALUE);
                running = false;
            }
        }
        program.restart();
    }

}
