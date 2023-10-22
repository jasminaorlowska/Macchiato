package Builders;

import Expressions.Expression;
import java.util.ArrayList;

public class List {
    public static ArrayList<Character> of(Character ... arguments) {
        ArrayList<Character> arr = new ArrayList<>();
        for (Character argument : arguments) {
            if (!arr.contains(argument)) arr.add(argument);
        }
        return arr;
    }
    public static ArrayList<Expression> of(Expression ... arguments) {
        ArrayList<Expression> arr = new ArrayList<>();
        for (Expression argument : arguments) {
            arr.add(argument);
        }
        return arr;
    }
}
