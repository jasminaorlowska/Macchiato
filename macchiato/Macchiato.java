package Macchiato;

import Instructions.Program;

import java.util.ArrayList;
import java.util.Scanner;

public final class Macchiato {

    private static Macchiato Instance;
    private final ArrayList<Program> programs;

    private Macchiato() {
        programs = new ArrayList<>();
    }

    public static Macchiato getInstance() {
        if(Instance == null) {
            Instance = new Macchiato();
        }
        return Instance;
    }

    public void showPrograms() {
        if (programs.isEmpty()) {
            System.out.println("No programs to show");
            return;
        }
        for (Program p : programs) {
            System.out.println(p.getName());
        }
    }

    // Programs are single blocks, so to create a program, you need to create a block.

    public Program.Builder createProgram(String name) {
        return new Program.Builder(name);
    }

    public void addProgram(Program p) {
        programs.add(p);
    }

    public Program getProgram(String name) throws IllegalArgumentException {
        for (Program p : programs) {
            if (name.equals(p.getName())) return p;
        }
        throw new IllegalArgumentException("Program named " + name + " doesn't exist");
    }

    public void runProgram(Program p) {
        Scanner scanner = new Scanner(System.in);
        Debugger debugger = new Debugger(p);
        System.out.println("Do you want to run the program with debugging? (y/n)");
        String input = scanner.next();
        if (input.equalsIgnoreCase("y")) {
            debugger.runWith();
        } else if (input.equalsIgnoreCase("n")) {
            debugger.runWithout();
        } else {
            // invalid input
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
        }
    }

    //invokes a program
    public void run(Program p) {
        Debugger debugger = new Debugger(p);
        debugger.runWithout();
    }

}