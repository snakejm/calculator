package com.jedi;

import java.util.Arrays;
import java.util.List;

class RomanConverter {
    private static final List<RomanInt> ROMAN_INT_LIST = Arrays.asList(RomanInt.values());
    private static final List<String> ROMAN_STING_LIST = ROMAN_INT_LIST.stream().map(Enum::toString).toList();

    public static String ArabicToRoman(int arabicInt) throws Exception {
        if (arabicInt < 1 || arabicInt > 3999) throw new Exception("Illegal output (output = " + arabicInt
                + ", Roman numbers must be only positive)");
        StringBuilder result = new StringBuilder();
        for (RomanInt romanInt : ROMAN_INT_LIST) {
            while (arabicInt >= romanInt.getArabic()) {
                result.append(romanInt);
                arabicInt -= romanInt.getArabic();
            }
        }
        return result.toString();
    }

    public static int RomanToArabic(String romanInt) throws Exception {
        int result = 0;
        if (isRoman(romanInt)) {
            if (ROMAN_STING_LIST.contains(romanInt)) {
                result = RomanInt.valueOf(romanInt).getArabic();
            } else {
                for (int i = 0; i < romanInt.length(); i++) {
                    result += RomanInt.valueOf(romanInt.substring(i, i + 1)).getArabic();
                }
            }
        }
        return result;
    }

    public static boolean isRoman(String number) {
        return number.matches("^(M{0,3})(D?C{0,3}|C[DM])(L?X{0,3}|X[LC])(V?I{0,3}|I[VX])$");
    }
}
