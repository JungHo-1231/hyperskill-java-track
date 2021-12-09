import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;


@Data
public class Card {
    private String cardNumber;
    private String pinNumber;
    private int balance;

    public Card() {
        cardNumber = generateNumber();
        pinNumber = generatePinNumber();
        balance = 0;
    }

    public Card(String cardNumber, String pinNumber, int balance) {
        this.cardNumber = cardNumber;
        this.pinNumber = pinNumber;
        this.balance = balance;
    }

    private String generateNumber() {
        Random random = new Random();
        String num = "400000" + (random.nextInt(899999999) + 100000000);
        return num + addLuhnNum(num);
    }

    private String addLuhnNum(String numForCheck) {
        numForCheck += "0";
        int oddSum = 0;
        int evenSum = 0;
        for (int x = numForCheck.length() - 1; x >= 0; x--) {
            int currentNum = Character.getNumericValue(numForCheck.charAt(x));
            if (x % 2 != 0) {
                oddSum += currentNum;
            } else {
                if (currentNum * 2 > 9) {
                    evenSum += currentNum * 2 - 9;
                } else {
                    evenSum += currentNum * 2;
                }
            }
        }
        int controlSum = oddSum + evenSum;
        return String.valueOf(controlSum % 10 == 0 ? 0 : 10 - controlSum % 10);
    }

    private String generatePinNumber() {
        return generateRandom(4);
    }

    private static String generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return new String(digits);
    }
}

