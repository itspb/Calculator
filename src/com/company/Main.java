package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String regExpArabic = "\\A(10|[1-9])\\s*([+\\-*/])\\s*(10|[1-9])\\z";
    private static String regExpRoman = "\\A(I|II|III|IV|V|VI|VII|VIII|IX|X)\\s*([+\\-*/])\\s*(I|II|III|IV|V|VI|VII|VIII|IX|X)\\z";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toUpperCase();

        if (input.matches(regExpArabic)) {
            System.out.println(convertArabicToMathAndCalculate(input));
        } else if (input.matches(regExpRoman)) {
            System.out.println(convertRomanToMathAndCalculate(input));
        } else {
            throw new Exception("Invalid input");
        }
    }

    private static int convertArabicToMathAndCalculate(String input) {
        Pattern pattern = Pattern.compile(regExpArabic);
        Matcher matcher = pattern.matcher(input);
        int result = 0;
        if (matcher.find()) {
            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(3));
            String operation = matcher.group(2);
            result = Calculate.calculate(a, b, operation);
        }
        return result;
    }

    private static String convertRomanToMathAndCalculate(String input) {
        Pattern pattern = Pattern.compile(regExpRoman);
        Matcher matcher = pattern.matcher(input);
        String result = null;
        if (matcher.find()) {
            int a = RomanNumbers.valueOf(matcher.group(1)).getValue();
            int b = RomanNumbers.valueOf(matcher.group(3)).getValue();
            String operation = matcher.group(2);
            int resultInt = Calculate.calculate(a, b, operation);
            result = RomanNumbers.values()[resultInt - 1].name();
        }
        return result;
    }
}
