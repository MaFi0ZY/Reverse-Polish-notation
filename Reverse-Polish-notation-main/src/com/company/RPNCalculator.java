package com.company;

import java.util.*;

public class RPNCalculator {
    public static HashMap<String, Operational> operationsToFunctions = new HashMap<>() {
        {
            put("+", new Operational() {

                @Override
                public int getParametersCount() {
                    return 2;
                }

                @Override
                public double calculate(Stack<Double> values) {
                    return values.pop() + values.pop();
                }
            });
            put("-", new Operational() {

                @Override
                public int getParametersCount() {
                    return 2;
                }

                @Override
                public double calculate(Stack<Double> values) {
                    return values.pop() - values.pop();
                }
            });
            put("*", new Operational() {

                @Override
                public int getParametersCount() {
                    return 2;
                }

                @Override
                public double calculate(Stack<Double> values) {
                    return values.pop() * values.pop();
                }
            });
            put("/", new Operational() {

                @Override
                public int getParametersCount() {
                    return 2;
                }

                @Override
                public double calculate(Stack<Double> values) {
                    return values.pop() / values.pop();
                }
            });
            put("^", new Operational() {

                @Override
                public int getParametersCount() {
                    return 2;
                }

                @Override
                public double calculate(Stack<Double> values) {
                    return Math.pow(values.pop(), values.pop());
                }
            });
            put("%", new Operational() {

                @Override
                public int getParametersCount() {
                    return 1;
                }

                @Override
                public double calculate(Stack<Double> values) {
                    return values.pop() / 100;
                }
            });
        }
    };

    public static double evalRPN(ArrayList<String> tokens) {
        Stack<Double> stack = new Stack<>();
        for (String t : tokens) {
            if (!operationsToFunctions.containsKey(t)) stack.push(Double.parseDouble(t));
            else {
                Operational op = operationsToFunctions.get(t);
                Stack<Double> params = new Stack<>();
                for(int i = 0; i < op.getParametersCount(); i++) {
                    params.push(stack.pop());
                }
                stack.push(op.calculate(params));
            }
        }
        return stack.pop();
    }
}
