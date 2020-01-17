package ua.nure.popova.practice3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

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
        String readed = readFile("part3.txt");
        System.out.println(readed);
        convert(readed);
    }

    //When I was younger
    //I never needed

    public static void convert(String input){
        System.out.println("---------------------------------");
        String withLines = transformAndSafeLines(input);
        String res = transform(withLines);
        System.out.println(res);
        System.out.println("---------------------------------");

        //when I Was Younger
        //I Never Needed
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

    private static String[] toArrayLines(String input){
        return input.split(LINE_SEPARATOR);
    }

    private static String[] toArrayWords(String input){
        return input.split("\\s");
    }

    private static String transform(String input){
        String[] lines = toArrayLines(input);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {
            String[] words = toArrayWords(lines[i]);

            for (int j = 0; j < words.length; j++) {
                if(i==0 && j==0){
                    sb.append(words[j].toLowerCase()).append(" ");
                    continue;
                }
                if (words[j].length() >= 3) {
                    sb.append(words[j].substring(0, 1).toUpperCase())
                            .append(words[j].substring(1)).append(" ");
                }else {
                    sb.append(words[j]).append(" ");
                }
            }

            sb.append(LINE_SEPARATOR);
        }
        return sb.toString().trim();
    }
}
