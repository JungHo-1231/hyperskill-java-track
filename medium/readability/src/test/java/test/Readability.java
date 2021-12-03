package test;

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

    private int getPolysyllables() {
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

    private int getSyllable() {
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
            case "all":
                getAri();
                getFk();
                getSmog();
                getCl();
                break;
        }
    }

    private void getCl() {
        final double l = 100. * characters / words;
        final double s = 100. * characters / words;
        double scoreResult = 0.0588 * l - 0.296 * s - 15.8;
        String fullName = "Coleman–Liau index";
        printScore(fullName, scoreResult);
    }

    private void getSmog() {
        double scoreResult = 1.043 * sqrt(totalPolysyllables * 30. / sentence) + 3.1291;
        String fullName = "Simple Measure of Gobbledygook";
        printScore(fullName, scoreResult);
    }

    private void getFk() {
        double scoreResult = 0.39 * words / sentence + 11.8 * syllables / words - 15.59;
        String fullName = "Flesch–Kincaid readability tests";
        printScore(fullName, scoreResult);
    }

    private void getAri() {
        double scoreResult = 4.71 * characters / words + 0.5 * words / sentence - 21.43;
        String fullName = "Automated test.Readability Index: %.2f (about %d year olds).\n";
        printScore(fullName, scoreResult);
    }

    private void printScore(String fullName, double scoreResult) {
        String.format("%s: %.2f (about %d year olds).%n", fullName, scoreResult, calculateAge(scoreResult));
    }

    private int calculateAge(final double score) {
        final int level = min(14, max(1, (int) ceil(score))) - 1;
        return new int[]{6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 24, 25}[level];
    }
}






























































