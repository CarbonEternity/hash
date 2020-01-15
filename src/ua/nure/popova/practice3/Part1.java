package ua.nure.popova.practice3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

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
        String readed = readFile("part1.txt");
        System.out.println(readed);
        System.out.println("---------------------------------");
        String mas = separateComma(readed);
        System.out.println(convert3(mas));
        System.out.println("---------------------------------");
        System.out.println(convert4(readed));
        System.out.println("---------------------------------");
    }

    public static String convert3(String mas) {
        String[] lines = mas.split(LINE_SEPARATOR);
        String domens = cutAllDomensFromAllStrings(lines);  //all domens
        String values = deleteTheSameDomens(lines, domens);
        return values.trim();
    }


    private static int random(){
         int min = 1000;
         int max = 9999;
         max -= min;
         return (int) (Math.random() * ++max) + min;
    }

    public static String convert4(String input) {
        String[] lines = input.split(LINE_SEPARATOR);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            if(i==0){
                sb.append(lines[i]).append(";Password").append(LINE_SEPARATOR);
            }else {
                sb.append(lines[i]).append(";").append(random()).append(LINE_SEPARATOR);
            }
        }

        return sb.toString().trim();
    }

    private static String addComma(String str) {
        Pattern p = Pattern.compile("(?mU)(\\w+)(\\s)(.+)");
        StringBuilder sb = new StringBuilder();

        Matcher m = p.matcher(str);
        while (m.find()) {
            sb.append(m.group(1)).append(", ").append(m.group(3));
        }

        return sb.toString().trim();
    }

    private static String buildLineWithDomen(String domen, String names) { //result
        StringBuilder sb = new StringBuilder();
        if (!domen.isEmpty() && !names.isEmpty()) {
            sb.append(domen).append(" ==> ").append(addComma(names)).append(LINE_SEPARATOR);
        }
        return sb.toString();
    }

    private static String findNamesWhichHasDomen(String[] lines, String domen) {
        String groupLineDomen = "";
        for (String line : lines) {
            if (line.contains(domen)) {
                groupLineDomen += cutWithoutDomens(line) + " ";
            }
        }
        return buildLineWithDomen(domen, groupLineDomen);
    }

    public static String cutAllDomensFromAllStrings(String[] inputs) {
        String email = "(?mU)^(.+)@(.+)$";
        Pattern p = Pattern.compile(email);
        StringBuilder sb = new StringBuilder();

        for (String input : inputs) {
            Matcher m = p.matcher(input);

            while (m.find()) {
                sb.append(m.group(2)).append(LINE_SEPARATOR);
            }
        }
        return sb.toString();
    }

    private static String deleteTheSameDomens(String[] lines, String domens) {
        String[] res = domens.split(LINE_SEPARATOR);
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
                sb.append(findNamesWhichHasDomen(lines, tmp));
            }
        }
        return sb.toString();
    }

    public static String cutWithoutDomens(String input) {
//        String email = "(?mU)^(\\S+;)(\\S+)(\\s+)(.+;)(.+)(@.+)";
        String email = "(?mU)^(\\S+)(\\s+)(\\S+)(.+)$";
        Pattern p = Pattern.compile(email);
        Matcher m = p.matcher(input);
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            sb.append(m.group(1));
        }
        return sb.toString();
    }

    private static String separateComma(String input) {
        final String REGEX = ";";
        final String REPLACE = " ";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, REPLACE);
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

}

