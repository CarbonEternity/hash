package ua.nure.popova.practice3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    private static final String ENCODING = "Cp1251";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static String readFile(String path) {
        String res = null;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            res = new String(bytes, ENCODING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        String readed = readFile("part2.txt");
        System.out.println(readed);
        System.out.println("---------------------------------");
        System.out.println(convert(readed));
    }

    public static String convert(String input){
        String mas = separate(input, " ");
        String[] separatedArray = toArray(mas);
        int minLength = findMinimumLength(separatedArray);
        System.out.println("minimum length = "+minLength);
        String mins = createMinMas(separatedArray, minLength);
        String unical = deleteTheSame(mins);
        String res = separate(unical.trim(), ", ");
        String output = "Min: "+ res;
        return output;
        //Min: I, s, m
    }

    private static String separate(String input, String replace) {
        final String REGEX = "\\W";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, replace);
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private static String[] toArray(String input){
        return input.split("\\s");
    }

    private static int findMinimumLength (String [] input){
        int tmp=input[1].length();
        for (String s : input) {
            if (s.length() > 0 && s.length() < tmp) {
                tmp = s.length();
            }
        }
        return tmp;
    }

    private static String createMinMas(String[] input, int minLength){
        StringBuilder sb = new StringBuilder();
        for (String s : input) {
            if (s.length() == minLength) {
                sb.append(s).append(" ");
            }
        }
        return sb.toString();
    }

    private static String deleteTheSame(String input) {
        String[] res = input.split(" ");
        ;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            String tmp = res[i];
            for (int j = 1; j < res.length; j++) {
                if (res[j].equals(tmp)) {
                    res[j] = "";
                }
            }
            if (!tmp.isEmpty()) {
                sb.append(tmp).append(" ");
            }
        }
        return sb.toString();
    }



}
