package main;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.*;

public class Readability {

    private String text;
    private int characters;
    private int words;
    private int sentence;
    private double score;
    private int syllables;
    private int totalPolysyllables;


    public Readability(String text) {
        this.text = text;
        setting();
    }

    private void setting() {
        characters = getCharacters();
        words = getWords();
        sentence = getSentence();
        syllables = getSyllable();
        totalPolysyllables = getPolysyllables();
    }

    int getPolysyllables() {
        Pattern pattern = Pattern.compile("(?!e\\b)[aeiouy]{1,}|\\b[0-9qwrtplkjhgfdszxcvbnm]+\\b|\\bthe\\b");
        Matcher matcher = pattern.matcher(text.toLowerCase());

        int polysyllables = 0;
        for (String word : text.split(" ")) {
            matcher = pattern.matcher(word.toLowerCase());
            int count = 0;
            while (matcher.find()) {
                count += 1;
            }
            polysyllables += count > 2 ? 1 : 0;
        }
        return polysyllables;
    }

    int getSyllable() {
        return text.split("[aeiouy]+[^$e(,.:;!?)]").length;
    }

    public int getCharacters() {
        String replace = text.replace(" ", "");
        return replace.length();
    }

    public int getSentence() {
        return text.split("[\\.\\?\\!\\:\\;]").length;
    }

    public double getScore() {
        int sentence = getSentence();
        int characters = getCharacters();
        int words = getWords();

        double score = 4.71 * characters / words + 0.5 * words / sentence - 21.43;
        String format = String.format("%.2f", score);
        return Double.parseDouble(format);
    }

    public int getWords() {
        return text.split(" ").length;
    }

    public String getAge() {
        double score = getScore();
        Integer integerScore = Integer.valueOf((int) score);
        integerScore++;
        switch (integerScore) {
            case 1:
                return "5-6";
            case 2:
                return "6-7";
            case 3:
                return "7-9";
            case 4:
                return "9-10";
            case 5:
                return "10-11";
            case 6:
                return "11-12";
            case 7:
                return "12-13";
            case 8:
                return "13-14";
            case 9:
                return "14-15";
            case 10:
                return "15-16";
            case 11:
                return "16-17";
            case 12:
                return "17-18";
            case 13:
                return "18-24";
            case 14:
                return "24+";
            default:
                return "Unknown";
        }
    }

    public void getReadabilityScores(String score) {

        switch (score) {
            case "ARI":
                getAri();
                break;
            case "FK":
                getFk();
                break;
            case "SMOG":
                getSmog();
                break;
            case "CL":
                getCl();
                break;
            case "ALL":
                double ari = getAri();
                double fk = getFk();
                double smog = getSmog();
                double cl = getCl();
                printAverage(ari, fk, smog, cl);
                break;
        }
    }

    private void printAverage(double... scores) {
        double average = Arrays.stream(scores).average().getAsDouble();
        System.out.printf("%.2f", average);
    }

    private double getCl() {
        final double l = 100. * characters / words;
        final double s = 100. * sentence / words;
        double scoreResult = 0.0588 * l - 0.296 * s - 15.8;
        String fullName = "Coleman–Liau index";
        printScore(fullName, scoreResult);
        return scoreResult;
    }

    private double getSmog() {
        double scoreResult = 1.043 * sqrt(totalPolysyllables * 30. / sentence) + 3.1291;
        String fullName = "Simple Measure of Gobbledygook";
        printScore(fullName, scoreResult);
        return scoreResult;
    }

    private double getFk() {
        double scoreResult = 0.39 * words / sentence + 11.8 * syllables / words - 15.59;
        String fullName = "Flesch–Kincaid readability tests";
        printScore(fullName, scoreResult);
        return scoreResult;
    }

    private double getAri() {
        String fullName = "Automated Readability Index";
        double scoreResult = 4.71 * characters / words + 0.5 * words / sentence - 21.43;
        printScore(fullName, scoreResult);

        return scoreResult;
    }

    private void printScore(String fullName, double scoreResult) {
        System.out.println(String.format("%s: %.2f (about %d year olds).%n", fullName, scoreResult, calculateAge(scoreResult)));
    }

    private int calculateAge(final double score) {
        final int level = min(14, max(1, (int) ceil(score))) - 1;
        return new int[]{6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 24, 25}[level];
    }

    public void start() {
        int words = getWords();
        System.out.println("words = " + words);

        int sentence = getSentence();
        System.out.println("sentence = " + sentence);

        int characters = getCharacters();
        System.out.println("characters = " + characters);

        int syllable = getSyllable();
        System.out.println("syllable = " + syllable);

        int polysyllables = getPolysyllables();
        System.out.println("polysyllables = " + polysyllables);

        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        getReadabilityScores(s.toUpperCase(Locale.ROOT));
    }
}






























































