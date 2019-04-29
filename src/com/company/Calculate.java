package com.company;

import java.util.HashMap;
import java.util.Map;

class Calculate {
    private static Map<String, Arithmetic> operations;
    static {
        operations = new HashMap<>();
        operations.put("+", new Add());
        operations.put("-", new Subtract());
        operations.put("*", new Multiply());
        operations.put("/", new Divide());
    }

    public static abstract class Arithmetic { public abstract int calculate(int a, int b); }

    public static class Add extends Arithmetic { public int calculate(int a, int b) { return a + b; } }
    public static class Subtract extends Arithmetic { public int calculate(int a, int b) { return a - b; } }
    public static class Multiply extends Arithmetic { public int calculate(int a, int b) { return a * b; } }
    public static class Divide extends Arithmetic { public int calculate(int a, int b) { return a / b; } }

    static int calculate (int a, int b, String operation) {
        return operations.get(operation).calculate(a, b);
    }
}
