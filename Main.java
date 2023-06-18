import Expressions.Expression;
import Instructions.*;
import Builders.List;
import Macchiato.Macchiato;
public class Main {

public static void main(String[] args) {

        Macchiato macchiato = Macchiato.getInstance();

            Program p = macchiato.createProgram("test").
            declareVariable('x', Expression.constant(101)).
            declareVariable('y',Expression.constant(1)).
            declareProcedure("out",
                    List.of('a'),
                    new Block.Builder().
                            print(Expression.sum(Expression.var('a'), Expression.var('x'))).
                            build()).
            assign('x', Expression.sum(Expression.var('x'), Expression.var('y'))).
            invoke("out", List.of(Expression.var('x'))).
            invoke("out", List.of(Expression.constant(100))).
            block(new Block.Builder().
                    declareVariable('x', Expression.constant(10)).
                    invoke("out", List.of(Expression.constant(100))).
                    build()).
            build();

        macchiato.addProgram(p);
        macchiato.runProgram(p);

        //
//            Program test1 = macchiato.createProgram("test1").
//                    declareVariable('a', Expression.constant(2)).
//                    iteration(new ForLoop.Builder(Expression.var('b'), Expression.var('a')).
//                            print(Expression.var('b')).
//                            build()).
//                    build();
//        macchiato.addProgram(test1);
//        macchiato.runProgram(test1);
    }
}

