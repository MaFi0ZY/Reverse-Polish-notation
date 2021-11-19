package com.company;

import java.util.Stack;

public interface Operational {
    int getParametersCount();
    double calculate(Stack<Double> values);
}
