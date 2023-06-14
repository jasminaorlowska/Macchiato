package Macchiato;

import Instructions.Program;
import Instructions.Variables;

import java.util.ArrayList;
import java.util.Scanner;

public final class Macchiato {

    private static Macchiato Instance;
    private ArrayList<Program> programs;

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
    public void createProgram(String name, Variables variables) {
        Program program = new Program(name, variables);
        programs.add(program);
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

}