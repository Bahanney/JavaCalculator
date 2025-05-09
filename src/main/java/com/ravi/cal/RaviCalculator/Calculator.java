package com.ravi.cal.RaviCalculator;

/**
 * A simple calculator class for basic arithmetic operations.
 */
public class Calculator {

    private long first;
    private long second;

    public Calculator(long first, long second) {
        this.first = first;
        this.second = second;
    }

    public long getFirst() {
        return first;
    }

    public long getSecond() {
        return second;
    }

    public long addFunc() {
        return first + second;
    }

    public long subFunc() {
        return first - second;
    }

    public long mulFunc() {
        return first * second;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: Please provide two numeric arguments.");
            return;
        }

        try {
            long first = Long.parseLong(args[0]);
            long second = Long.parseLong(args[1]);

            Calculator cal = new Calculator(first, second);

            System.out.printf(
                "\n*** Your Results ***\n\n" +
                "First: %d\n" +
                "Second: %d\n\n" +
                "Sum: %d\n" +
                "Difference: %d\n" +
                "Product: %d\n\n",
                first, second, cal.addFunc(), cal.subFunc(), cal.mulFunc()
            );

        } catch (NumberFormatException e) {
            System.out.println("Error: Arguments must be valid numbers.");
        }
    }
}
