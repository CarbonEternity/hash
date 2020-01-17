package ua.nure.popova.practice3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

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
        String readed = readFile("part6.txt");
        System.out.println(readed);
        System.out.println("---------------------------------");
        System.out.println(convert(readed));
        System.out.println("---------------------------------");
    }

    public static String convert(String input) {
        String withLines = transformAndSafeLines(input);
        return underlineTheSame(withLines);
    }

    public static String transformAndSafeLines(String input) {
        String regex = "(?mU)(.+)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            sb.append(m.group(1)).append(LINE_SEPARATOR);
        }
        return sb.toString();
    }

    private static String linesToWords(String input) {
        String regex = "(?mU)(\\w+)(\\s)?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            sb.append(m.group(1)).append(" ");
        }
        return sb.toString();
    }

    private static String underlineTheSame(String input) {
        String all = linesToWords(input);
        String[] lines = input.split(LINE_SEPARATOR);
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < lines.length; j++) {
            String[] wordsInLine = lines[j].split("\\s");
            for (int i = 0; i < wordsInLine.length; i++) {
                String word = wordsInLine[i];

                if (isPresentMore(all, word)) {
                    sb.append("_").append(word).append(" ");
                } else {
                    sb.append(word).append(" ");
                }
            }
            sb.append(LINE_SEPARATOR);
        }
        return sb.toString().trim();
    }

    private static boolean isPresentMore(String data, String tmp) {
        String[] input = data.split("\\s");
        int count = 0;
        for (String s : input) {
            if (s.equals(tmp)) {
                count++;
            }
        }
        return count > 1;
    }

}
