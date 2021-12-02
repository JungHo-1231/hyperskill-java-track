import java.util.Scanner;

public class Room {
    public static final char SEAT = 'S';
    public static final char BUY = 'B';
    private static char[][] cinemaHall;
    private RoomStatic roomStatic;
    private static final Scanner scan = new Scanner(System.in);
    private static int numberOfPurchased;
    private static int currentIncome;

    public Room(int rows, int seats) {
        roomStatic = new RoomStatic();
        initCinemaHall(rows, seats);
    }

    private void initCinemaHall(int rows, int seats) {
        cinemaHall = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinemaHall[i][j] = SEAT;
            }
        }
    }

    public void startUI() {
        System.out.println();
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.print("0. Exit\n> ");
            int option = scan.nextInt();

            if (option == 1) {
                showSeats();
            } else if (option == 2) {
                buyTicket();
            } else if (option == 3) {
                printStatics();
            } else if (option == 0) {
                break;
            }
        }
    }

    private void printStatics() {
        System.out.println("Number of purchased tickets: " + numberOfPurchased);

        float totalIncome = roomStatic.getTotalIncome(cinemaHall);
        float percentageOfPurchased =  roomStatic.getPercentageOfPurchased(cinemaHall, numberOfPurchased);

        System.out.printf("Percentage: %.2f%s%n", percentageOfPurchased, "%");

        System.out.println("Current income: $" + currentIncome);

        System.out.println("Total income: $" + totalIncome);
    }

    private void showSeats() {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= cinemaHall[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < cinemaHall.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cinemaHall[0].length; j++) {
                System.out.print(" " + cinemaHall[i][j]);
            }
            System.out.println();
        }
    }

    private void buyTicket() {
        int totalNumberOfSeats = cinemaHall.length * cinemaHall[0].length;

        int seatRow;
        int seatNumber;

        while (true) {
            System.out.println("Enter a row number:");
            seatRow = scan.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scan.nextInt();
            if (seatRow > cinemaHall.length || seatNumber > cinemaHall[0].length) {
                System.out.println("Wrong input!");
            } else {
                if (cinemaHall[seatRow - 1][seatNumber - 1] == BUY) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    break;
                }
            }
        }

        int priceTicket = roomStatic.getPriceTicket(totalNumberOfSeats, cinemaHall, seatRow);

        currentIncome += priceTicket;
        numberOfPurchased++;
        System.out.println("Ticket price: $" + priceTicket);
        cinemaHall[seatRow - 1][seatNumber - 1] = BUY;
    }
}
