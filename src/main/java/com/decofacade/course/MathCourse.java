package com.decofacade.course;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class MathCourse implements Course{
    private String[] Pages;
    public int CurrentPage;

    @Override
    public Course Load(String path) {
        CurrentPage = 0;
        StringBuilder contentBuilder = new StringBuilder();


        try (FileReader fr = new FileReader("src/main/java/com/decofacade/courseFile/"+path);
             Scanner scanner = new Scanner(fr)) {

            while (scanner.hasNextLine())
                contentBuilder.append(scanner.nextLine()).append("\n");

            String content = contentBuilder.toString();
            Pages = Arrays.stream(content.split(";"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toArray(String[]::new);

        } catch (IOException e) {
            System.err.println("IOEX: " + e.getMessage());
        }
        return this;
    }

    @Override
    public void TextFromPage(int page) {
        pageCount = Pages.length;
        CurrentPage = page;

        String text = Pages[page];
        String[] words = text.split("\\s+");

        if (words.length == 0) return;

        String firstWord = words[0].toUpperCase(Locale.ROOT);
        switch (firstWord) {
            case "TEST" -> {
                if (words.length >= 2) {
                    String answer = words[1];
                    System.out.println(text);
                    Test(answer);

                } else {
                    System.out.println("no responce 505 500 400 error you did wronf ... " + page);
                }
            }
            case "END" -> {
                System.out.println("end.");
                System.out.println(text);
            }
            default -> {
                System.out.println(text);
            }
        }
    }

    @Override
    public boolean NextPage() {

        if (CurrentPage + 1 < Pages.length) {
            TextFromPage(++CurrentPage);
            return false;
        }
        else
            System.out.println("Last page: "+CurrentPage+1);
        return true;
    }

    @Override
    public boolean PreviousPage() {

        if (CurrentPage - 1 >= 0) {
            TextFromPage(--CurrentPage);
            return false;
        }
        else
            System.out.println("First page");
        return true;
    }

    @Override
    public void Test(String answer) {
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().equals(answer)) {
            System.out.println("Правильно молодец!");
        }
        else {
            System.out.println("Не правильно не молодец");
        }
    }

    public int getCurrentPage() {
        return CurrentPage;
    }

    public int pageCount = 0;
}
