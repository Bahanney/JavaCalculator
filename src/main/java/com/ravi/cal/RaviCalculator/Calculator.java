package com.ravi.cal.RaviCalculator;

public class Calculator {

    private long first;
    private long second;

    public Calculator(long first, long second){
        this.first = first;
        this.second = second;
    }

    public long getFirst() {
        return first;
    }

    public long getSecond() {
        return second;
    }

    public long addFucn(){
        return first + second;
    }

    public long subFucn(){
        return first - second;
    }

    public long mulFucn(){
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
            String output = String.format(
                "\n*** Your Results ***\n\nFirst: %d\nSecond: %d\n\nSum : %d\nDifference : %d\nProduct : %d\n\n",
                cal.getFirst(), cal.getSecond(), cal.addFucn(), cal.subFucn(), cal.mulFucn());

            System.out.println(output);
        } catch (NumberFormatException e) {
            System.out.println("Error: Arguments must be valid numbers.");
        }
    }
}
