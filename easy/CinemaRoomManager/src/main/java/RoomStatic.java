public class RoomStatic {
    private static final int priceNotMore60Number = 10;
    private static final int priceFrontHalf = 10;
    private static final int priceBackHalf = 8;


    public int getPriceTicket(int totalNumberOfSeats, char[][] cinemaHall, int seatRow) {

        int priceTicket;

        if (totalNumberOfSeats <= 60) {
            priceTicket = priceNotMore60Number;
        } else {
            int frontHalfOfRows = cinemaHall.length / 2;
            if (seatRow <= frontHalfOfRows) {
                priceTicket = priceFrontHalf;
            } else {
                priceTicket = priceBackHalf;
            }
        }
        return priceTicket;
    }

    public int getTotalIncome(char[][] cinemaHall) {
        int totalIncome;

        int totalNumOfSeats = cinemaHall.length * cinemaHall[0].length;
        if (totalNumOfSeats <= 60) {
            totalIncome = totalNumOfSeats * priceNotMore60Number;
        } else {
            int frontHalfOfRows = cinemaHall.length / 2;
            int totalFrontHalf = frontHalfOfRows * cinemaHall[0].length * priceFrontHalf;
            int totalBackHalf = (cinemaHall.length - frontHalfOfRows) * cinemaHall[0].length * priceBackHalf;
            totalIncome = totalFrontHalf + totalBackHalf;
        }
        return totalIncome;
    }

    public float getPercentageOfPurchased(char[][] cinemaHall, int numberOfPurchased) {
        float totalNumOfSeats = cinemaHall.length * cinemaHall[0].length;
        return 100 / totalNumOfSeats * numberOfPurchased;
    }
}
