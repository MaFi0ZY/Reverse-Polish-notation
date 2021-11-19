package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("enter expression (and press enter): ");
        String num = in.nextLine().replace("( -", "( 0 -");
        if (num.charAt(0) == '-') {
            num = "0 " + num;
        }
        in.close();

        String[] subStr;
        subStr = num.split(" ");

        try {
            ArrayList<String> RPN = RPNConverter.convertToRPN(subStr);
            System.out.println("reverse polish notation is:" + RPN);
            System.out.println("result: " + RPNCalculator.evalRPN(RPN));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}