package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String text = new Scanner(new File(args[0])).nextLine();
        Readability readability = new Readability(text);
        readability.start();
    }
}
