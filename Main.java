import Expressions.Expression;
import Instructions.*;
import Builders.List;
import Macchiato.Macchiato;
public class Main {

public static void main(String[] args) {

        Macchiato macchiato = Macchiato.getInstance();
    /*var program = new ProgramBuilder()
    .declareVariable('x', Constant.of(101))
    .declareVariable('y', Constant.of(1))
    .declareProcedure('out', List.of('a'), new BlockBuilder()
         .print(Addition.of(Variable.named('a'), Variable.named('x')))
         .build()
    )
    .assign('x', Subtraction.of(Variable.named('x'), Variable.named('y')))
    .invoke('out', List.of(Variable.named('x')))
    .invoke('out', List.of(Constant.of(100)))
    .block(new BlockBuilder()
        .declareVariable('x', Constant.of(10))
        .invoke('out', List.of(Constant.of(100)))
        .build()
    )
    .build();*/

            /*begin block: x,y, out('a')
                out (1) -> print 1 + x
            */

            Program p = macchiato.createProgram("test").
            declareVariable('x', Expression.constant(101)).
            declareVariable('y',Expression.constant(1)).
            declareProcedure("out",
                    List.of('a'),
                    new Block.Builder().
                            print(Expression.sum(Expression.var('a'), Expression.var('x'))).build()).
                    invoke("out", List.of(Expression.constant(1))).
                    build();
            macchiato.addProgram(p);
            macchiato.runProgram(p);
    }
}

