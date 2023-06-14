package Exceptions;
/**End of steps exceptions - while debugging*/
public class EndOfStepsException extends Exception{
    public EndOfStepsException(String message) {
        super("Next instruction: " + message);
    };

}
