package Exceptions;

public class UndefinedVariableException extends Exception{
    public UndefinedVariableException(char c) {
        super("MacchiatoInstructions.Variable " + c + " is undefined.");
    }
}
