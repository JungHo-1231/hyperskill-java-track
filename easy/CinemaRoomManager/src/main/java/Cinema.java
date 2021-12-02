import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the number of rows:\n> ");
        int rows = scan.nextInt();

        System.out.print("Enter the number of seats in each row:\n> ");
        int seats = scan.nextInt();

        Room room = new Room(rows, seats);
        room.startUI();
    }
}
