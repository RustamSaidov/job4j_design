package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private boolean stopFlag = false;
    private boolean outFlag = false;
    List<String> log = new ArrayList<>();
    List<String> randomPhrasesList = new ArrayList<>();
    private final String path;
    private final String botAnswers;


    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        randomPhrasesList = readPhrases();
        String randomAnswer;
        Scanner sc = new Scanner(System.in);
        while (!outFlag) {
            System.out.println("Please write any Phrase:");
            String str = sc.nextLine();
            log.add(str);
            if (Objects.equals(str, OUT)) {
                outFlag = true;
            } else if (Objects.equals(str, STOP)) {
                stopFlag = true;
            } else if (Objects.equals(str, CONTINUE)) {
                stopFlag = false;
            } else if (!stopFlag) {
                randomAnswer = randomPhrasesList.get((int) (Math.random() * randomPhrasesList.size()));
                System.out.println("Bot answers:");
                System.out.println(randomAnswer);
                log.add(randomAnswer);
            }
        }
        sc.close();
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrasesList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                phrasesList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrasesList;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path)))) {
            for (int i = 0; i < log.size(); i++) {
                out.println(log.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\data\\dialog_with_bot.txt",
                "C:\\projects\\job4j_design\\data\\random_phrases_list.txt");
        cc.run();
    }
}
