package com.jedi;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static String calc(String input) throws Exception {
        String[] lines = input.trim().split(" ");
        if (lines.length != 3) throw new Exception("Illegal input (expected \"A operator(+, -, *, /) B\")");
        boolean aIsRoman = RomanConverter.isRoman(lines[0]);
        boolean bIsRoman = RomanConverter.isRoman(lines[2]);
        if (aIsRoman != bIsRoman) {
            throw new Exception("Illegal input (both numbers must be only Roman or Arabic at the same time)");
        }
        if (!((lines[1].length() == 1) && (lines[1].matches("[+\\-*/]")))) {
            throw new Exception("Illegal operator (expected +, -, *, /)");
        }
        int a, b;
        if (aIsRoman) {
            a = RomanConverter.RomanToArabic(lines[0]);
            b = RomanConverter.RomanToArabic(lines[2]);
            if ((a < 1 || a > 10) || (b < 1 || b > 10)) throw new Exception("Illegal number (expected I...X)");
        } else {
            a = Integer.parseInt(lines[0]);
            b = Integer.parseInt(lines[2]);
            if ((a < 1 || a > 10) || (b < 1 || b > 10)) throw new Exception("Illegal number (expected 1...10)");
        }
        int y = switch (lines[1]) {
            case ("+") -> a + b;
            case ("-") -> a - b;
            case ("*") -> a * b;
            case ("/") -> a / b;
            default -> throw new Exception("Something wrong");
        };
        String result = "";
        if (aIsRoman) {
            result = RomanConverter.ArabicToRoman(y);
        } else result = String.valueOf(y);
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while (!(line = reader.readLine()).equalsIgnoreCase("exit")){
                System.out.println(calc(line));
        }
    }
}
