import Expressions.Expression;
import Instructions.*;
import Builders.List;
import Macchiato.Macchiato;
public class Main {

public static void main(String[] args) {

        Macchiato macchiato = Macchiato.getInstance();

        //Program przykladowy 1
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

        /*begin block: var a = 2
            for (b = 0; b < a; b++)
                print b
          end block
        * */
        Program test1 = macchiato.createProgram("test1").
                declareVariable('a', Expression.constant(2)).
                iteration(new ForLoop.Builder(Expression.var('b'),
                            Expression.var('a')).
                            print(Expression.var('b')).
                            build()).
        build();
        macchiato.addProgram(test1);
        macchiato.runProgram(test1);

        /*begin block: a = 3
            if a > 1:
                print a+1
        * */
        Program test2 = macchiato.createProgram("test2").
                declareVariable('a', Expression.constant(3)).
                conditionalStatement(
                        new If.Builder(Expression.var('a'), ">", Expression.constant(1)).
                                print(Expression.sum(Expression.var('a'), Expression.constant(1))).
                        build()).
                build();
        macchiato.addProgram(test2);
        macchiato.runProgram(test2);


        /*begin block: c = 0, "procedure"
                run procedure
         end block
        * */
        Program test3 = macchiato.createProgram("test3").
                declareVariable('c', Expression.constant(10)).
                declareProcedure("procedure", List.of('a', 'b'),
                        new PrintExpression(Expression.var('a')),
                        new PrintExpression(Expression.var('b')),
                        new ChangeValueVariable(
                                Expression.var('a'),
                                Expression.sum(Expression.var('a'), Expression.var('b'))
                        ),
                        new PrintExpression(Expression.var('a'))).
                invoke("procedure", List.of(Expression.constant(1), Expression.var('c'))).
                build();
        macchiato.addProgram(test3);
        macchiato.runProgram(test3);
    }
}

