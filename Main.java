import Expressions.*;
import Instructions.*;
import Macchiato.Macchiato;

public class Main {

public static void main(String[] args) {

        Macchiato macchiato = Macchiato.getInstance();

        /*Pare uwag:
        instrukcje musimy dodawac po kolei do kazdej instrukcji zlozonej
        Programy mozna uruchamiac przez macchiato podajac nazwe (jak sie tworzy tak jak ja zrobilam)
        albo bezposrednio tworzac program przez new Program */


        //tworzenie glownego bloku programu
        Variables variablesMain = new Variables();
        Variable n = new Variable('n',new IntegerLiteral(30));
        variablesMain.addVariable(n);
        macchiato.createProgram("test", variablesMain);

        Program program = macchiato.getProgram("test");

        //Tworzenie wiekszego fora
        Variable k = new Variable('k');
        ForLoop f = new ForLoop(k, new Expressions.Subtraction(n, new IntegerLiteral(1)));
        program.addInstruction(f);

        //Tworzenie wnetrza duzego fora
        Variables varsBigFor = new Variables();
        Variable p = new Variable('p', new IntegerLiteral(1));
        varsBigFor.addVariable(p);
        Block b = new Block(varsBigFor);
        f.addInstruction(b);

        //Uzupelnianie wnetrza duzego fora, zmienne
        ChangeValueVariable changeVal = new ChangeValueVariable(k, new Expressions.Sum(k, new IntegerLiteral(2)));
        b.addInstruction(changeVal);

        //Uzupelnianie wnetrza duzego fora, maly for
        Variable i = new Variable('i');
        ForLoop f2 = new ForLoop(i, new Expressions.Subtraction(k, new IntegerLiteral(2)));
        b.addInstruction(f2);
        f2.addInstruction(new ChangeValueVariable(i, new Expressions.Sum(i, new IntegerLiteral(2))));

        Expressions.Modulo m = new Expressions.Modulo(k, i);
        If ifInFor = new If(m, new IntegerLiteral(0), "=");
        ifInFor.addInstruction(new ChangeValueVariable(p, new IntegerLiteral(0)));
        f2.addInstruction(ifInFor);

        //Uzupelnienie duzego fora, if
        If ifOutsideFor = new If(p, new IntegerLiteral(1), "=");
        PrintExpression print = new PrintExpression(p);
        ifOutsideFor.addInstruction(print);
        b.addInstruction(ifOutsideFor);

        macchiato.runProgram(program);


        /*
        //Prosty program
        Set<Variable> variablesMain = new HashSet<>();
        Variable a = new Variable('a', new IntegerLiteral(1));
        Variable b = new Variable('b', new IntegerLiteral(2));
        Variable c = new Variable('c', new IntegerLiteral(15));
        variablesMain.add(a);
        variablesMain.add(b);
        variablesMain.add(c);
        macchiato.createProgram("test2", variablesMain);
        Program p = macchiato.getProgram("test2");
        p.addInstruction(new ChangeValueVariable(c, new Expressions.Division(new IntegerLiteral(2), b)));

        ForLoop f = new ForLoop(a, new Expressions.Sum(new IntegerLiteral(1), new IntegerLiteral(3)));
        f.addInstruction(new PrintExpression(a));
        p.addInstruction(f);
        macchiato.runProgram(p);
        */

        /*
        //Prosty program wersja z wylapywaniem bledow
        Set<Variable> variablesMain = new HashSet<>();
        Variable a = new Variable('a', new IntegerLiteral(1));
        Variable b = new Variable('b');
        Variable c = new Variable('c', new IntegerLiteral(15));
        Variable d = new Variable('d');
        variablesMain.add(a);
        variablesMain.add(b);
        variablesMain.add(c);
        variablesMain.add(d);
        macchiato.createProgram("test3", variablesMain);
        Program p = macchiato.getProgram("test3");
        Block block = new Block();
        p.addInstruction(block);

        //odkomentowywanie po kolei
        //Błąd 1
        block.addInstruction(new ChangeValueVariable(d, new Expressions.Division(a,b)));
        //Bład 2
        Expressions.Sum s = new Expressions.Sum(new Variable('e'),b);
//        p.addInstruction(new ChangeValueVariable(d, s));
        //Błąd 3
//        p.addInstruction(new ChangeValueVariable(c, new Expressions.Modulo(a, b)));
        macchiato.runProgram(p);
        */

//        /*
        //Prosty program przyklad dostepu do wartosci nie ze swojego bloku
//        Set<Variable> variablesMain = new HashSet<>();
//        Set<Variable> variablesBlock1 = new HashSet<>();
//        Set<Variable> variablesBlock2 = new HashSet<>();
//        Variable a = new Variable('a', new IntegerLiteral(1));
//        Variable b = new Variable('b');
//        Variable c = new Variable('c', new IntegerLiteral(15));
//        Variable d = new Variable('d');
//        variablesBlock1.add(a);
//        variablesBlock2.add(b);
//        variablesMain.add(c);
//        variablesMain.add(d);
//        macchiato.createProgram("test3", variablesMain);
//        Program p = macchiato.getProgram("test3");
//        Block block1 = new Block(variablesBlock1);
//        Block block2 = new Block(variablesBlock2);
//        p.addInstruction(block1);
//        p.addInstruction(block2);
//
//        //blok 2 nie ma dostepu do zmiennych z innych blokow (oprocz tych w ktorych on sie zawiera)
//        block2.addInstruction(new ChangeValueVariable(b, c));
////        block2.addInstruction(new ChangeValueVariable(c, a));
//
//        macchiato.runProgram(p);
//        */
    }
}

