import org.sqlite.SQLiteDataSource;

import javax.xml.namespace.QName;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static CardsManagementSystem managementSystem;
    static Card authorizedCard;
    static SQLiteDataSource sds;

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:".concat(args[1]);
        sds = new SQLiteDataSource();
        try {
            managementSystem = new CardsManagementSystem(sds);
            launchMainMenu();
        } catch (InputMismatchException | SQLException e) {
            System.out.println("wrong input");
            launchMainMenu();
        }
    }

    static void launchMainMenu() throws SQLException {
        while (true) {
            System.out.println("\n1. Create an account\n2. Log into account\n0. Exit");
            switch (scanner.nextInt()) {
                case 1:
                    Card savedCard = managementSystem.addCard();
                    System.out.printf("\nYour card has been created\nYour card number:\n%s\nYour card PIN:\n%s\n",
                            savedCard.getCardNumber(), savedCard.getPinNumber());
                    break;
                case 2:
                    launchLoginMenu(managementSystem);
                    break;
                case 0:
                    System.out.println("\nBye!");
                    return;
            }
        }
    }

    private static void launchLoginMenu(CardsManagementSystem managementSystem) {
        while (true) {
            System.out.println("Enter your card number:");
            String cardNumber = scanner.next();
            System.out.println("Enter your PIN:");
            String cardPin = scanner.next();
            try {
                authorizedCard = managementSystem.findAccount(cardNumber, cardPin);
                System.out.println("You have successfully logged in!");
                printLoginMenu(authorizedCard);
                break;
            } catch (IllegalArgumentException e) {
                e.getMessage();
                System.out.println("Wrong card number or PIN!");
                break;
            }
        }
    }

    private static void printLoginMenu(Card account) {
        while (true) {
            System.out.println("1. Balance\n" +
                    "2. Log out\n" +
                    "0. Exit");

            int loginMenu = scanner.nextInt();

            if (loginMenu == 1) {
                System.out.printf("Balance: %s\n", account.getBalance());
            }

            if (loginMenu == 2) {
                authorizedCard = null;
                return;
            }

            if (loginMenu == 0) {
                System.exit(0);
                return;
            }
        }
    }
}
