package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    //    private final String filePath = "/Users/jh/IdeaProjects/ReadabilityScore/src/main/java/text.txt";
    private final String filePath = "/Users/jh/IdeaProjects/readability/src/main/java/text.txt";
    private File file;
    private String text;
    Readability readability;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        file = new File(filePath);
        text = new Scanner(file).nextLine();
        readability = new Readability(text);
    }

    @Test
    void fileExistTest() throws Exception{
        boolean exists = file.exists();
        assertEquals(true, exists);
    }

    @Test
    void fileContainsTextTest () throws Exception{
        assertTrue(text.contains("test.Readability"));
    }

    @Test
    void charactersTest() throws Exception{
        int characters = readability.getCharacters();
        assertEquals(characters, 580);
    }

    @Test
    void sentenceTest() throws Exception{
        int sentence = readability.getSentence();
        assertEquals(sentence, 6);
    }

    @Test
    void wordTest() throws Exception{
        int words = readability.getWords();
        assertEquals(words, 108);
    }

    @Test
    void scoreTest() throws Exception{
        double score = readability.getScore();
        assertEquals(score, 12.86);
    }

    @Test
    void understoodByAgeTest() throws Exception{
        String age = readability.getAge();
        String expected = "18-24";
        assertEquals(expected, age);
    }

    @Test
    void getReadabilityScores() throws Exception{
        readability.getReadabilityScores("ARI");
    }
}
