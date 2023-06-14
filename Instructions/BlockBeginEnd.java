/**Instrukcja wejscia/wyjscia z bloku, argument true oznacza, ze instrukcja jest instrukcja "begin block"
 * a false, ze to instrukcja "end block "**/

package Instructions;
import Exceptions.EndOfStepsException;
import Macchiato.Debugger;

public class BlockBeginEnd extends Instruction {

    private final String message;
    private final boolean begin;

    //entering, exiting block
    public BlockBeginEnd(boolean begin) {
        this.begin = begin;
        if (begin) {
            this.message = "begin block";
        }
        else {
            this.message = "end block";
        }
    }

    public void run(Debugger d) throws EndOfStepsException {
        if (d.getSteps() == 0) {
            throw new EndOfStepsException(toString());
        }

        setRun(true);
        d.changeSteps();

        if (begin) {
            d.stackPush(this.getParentBlock());
            this.getParentBlock().setStartedRunning(true);
        }
        else {
            d.stackPop();
            this.getParentBlock().setRun(true);
        }
    }

    public String toString() {
        return this.message;
    }

}
