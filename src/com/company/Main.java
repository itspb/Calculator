package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String regexArabic = "\\A(10|[1-9])\\s*([+\\-*/])\\s*(10|[1-9])\\z";
    private static String regexRoman = "\\A(I|II|III|IV|V|VI|VII|VIII|IX|X)\\s*([+\\-*/])\\s*(I|II|III|IV|V|VI|VII|VIII|IX|X)\\z";
    private static Calculator calculator;

    public static void main(String[] args) throws Exception {
        System.out.println("Arabic and Roman numbers from 1 to 10 inclusive are allowed.\nEnter \"exit\" for exit.");
        Scanner scanner = new Scanner(System.in);
        calculator = new Calculator();
        String input;
        while (!scanner.hasNext("exit")) {
            input = scanner.nextLine().toUpperCase();

            if (input.matches(regexArabic)) {
                System.out.println(convertArabicToMathAndCalculate(input));
            } else if (input.matches(regexRoman)) {
                System.out.println(convertRomanToMathAndCalculate(input));
            } else {
                throw new Exception("Invalid input");
            }
        }
    }

    private static int convertArabicToMathAndCalculate(String input) {
        Pattern pattern = Pattern.compile(regexArabic);
        Matcher matcher = pattern.matcher(input);
        int result = 0;
        if (matcher.find()) {
            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(3));
            String operation = matcher.group(2);
            result = calculator.calculate(a, b, operation);
        }
        return result;
    }

    private static String convertRomanToMathAndCalculate(String input) throws Exception {
        Pattern pattern = Pattern.compile(regexRoman);
        Matcher matcher = pattern.matcher(input);
        String result = null;
        if (matcher.find()) {
            int a = RomanNumbers.valueOf(matcher.group(1)).getValue();
            int b = RomanNumbers.valueOf(matcher.group(3)).getValue();
            String operation = matcher.group(2);
            int resultInt = calculator.calculate(a, b, operation);
            if (resultInt < 1) throw new Exception("The result is less than one and cannot be represented as Roman number");
            result = RomanNumbers.values()[resultInt - 1].name();
        }
        return result;
    }
}
