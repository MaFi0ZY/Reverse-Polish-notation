package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class RPNConverter {

    public static ArrayList<String> convertToRPN(String[] expression) throws Exception {

        if (!isExpressionValid(expression)) throw new Exception("Invalid expression");

        ArrayList<String> result = new ArrayList<>();
        if (expression == null || expression.length == 0) {
            return result;
        }
        Stack<String> opStack = new Stack<>();
        for (String token : expression) {
            if (isNumber(token)) {
                result.add(token);
            } else if (token.equals("(")) {
                opStack.push(token);
            } else if (token.equals(")")) {
                while (!opStack.peek().equals("(")) {
                    result.add(opStack.pop());
                }
                opStack.pop();
            } else {
                while (!opStack.isEmpty() && getPriority(opStack.peek()) >= getPriority(token)) {
                    result.add(opStack.pop());
                }
                opStack.push(token);
            }
        }
        while (!opStack.isEmpty()) {
            result.add(opStack.pop());
        }
        return result;
    }

    private final static ArrayList<String> validSymbols = new ArrayList<>(RPNCalculator.operationsToFunctions.keySet()) {
        {
            add("(");
            add(")");
        }
    };

    private static boolean isExpressionValid(String[] expression) {
        boolean isValid = Arrays.stream(expression).allMatch(x -> isNumber(x) || validSymbols.contains(x));
        if (!isValid) return  false;
        return Arrays.stream(expression).filter(x -> x.equals("(")).count() ==
                Arrays.stream(expression).filter(x -> x.equals(")")).count();
    }

    private static boolean isNumber(String token) {
        return Character.isDigit(token.charAt(0));
    }

    private static int getPriority(String op) {
        if (op.equals("(")) {
            return 0;
        } else if (op.equals("+") || op.equals("-")) {
            return 1;
        }
        else if (op.equals("*") || op.equals("/") || op.equals("%")) {
            return 2;
        }else {
            return 3;
        }
    }
}
