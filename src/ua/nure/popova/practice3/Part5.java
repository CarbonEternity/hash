package ua.nure.popova.practice3;

public class Part5 {

    private static int valueOfSymbol(char r) {
        switch (r) {
            case 'I': {
                return 1;
            }
            case 'V': {
                return 5;
            }
            case 'X': {
                return 10;
            }
            case 'L': {
                return 50;
            }
            case 'C': {
                return 100;
            }
            default: {
                throw new NumberFormatException(
                        "Illegal character " + r);
            }
        }
    }

    private static String symbolOfValue(int value) {
        switch (value) {
            case 1: {
                return "I";
            }
            case 4: {
                return "IV";
            }
            case 5: {
                return "V";
            }
            case 9: {
                return "IX";
            }
            case 10: {
                return "X";
            }
            case 40: {
                return "XL";
            }
            case 50: {
                return "L";
            }
            case 90: {
                return "XC";
            }
            case 100: {
                return "C";
            }
            default: {
                return " ";
            }
        }
    }

    private static final int [] TEST_INPUT = {1, 2, 3, 4, 5, 94, 95, 97, 98, 99, 100};

    public static void main(String[] args) {

        if (args.length > 1) {
            for (String arg : args) {
                int input = Integer.parseInt(arg);
                System.out.println(input);
                converter(input);
            }
        }else{
            for (int i : TEST_INPUT) {
                converter(i);
            }
        }
    }

    private static void converter(int input) {
        String roman = decimal2Roman(input);
        int decimal = roman2Decimal(roman);
        System.out.println(input + " --> " + roman + " --> " + decimal);
        if(input==5){
            System.out.println("...");
        }
    }

    /*
1 --> I --> 1
2 --> II --> 2
3 --> III --> 3
4 --> IV --> 4
5 --> V --> 5
...
94 --> XCIV --> 94
95 --> XCV --> 95
96 --> XCVI --> 96
97 --> XCVII --> 97
98 --> XCVIII --> 98
99 --> XCIX --> 99
100 --> C --> 100*/

    private static String decimal2Roman(int input) {
        int[] romans = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder s = new StringBuilder(" ");

        for (int i = 0; i < romans.length; i++) {

            int end = romans[i];

            while (input >= end) {
                s.append(symbolOfValue(end));
                input -= end;
            }
        }
        return s.toString().trim();
    }

    public static int roman2Decimal(String str) {
        int res = 0;

        for (int i = 0; i < str.length(); i++) {

            int s1 = valueOfSymbol(str.charAt(i));

            if (i + 1 < str.length()) {
                int s2 = valueOfSymbol(str.charAt(i + 1));

                if (s1 >= s2) {
                    res = res + s1;
                } else {
                    res = res + s2 - s1;
                    i++;
                }
            } else {
                res = res + s1;
                i++;
            }
        }
        return res;
    }
}
