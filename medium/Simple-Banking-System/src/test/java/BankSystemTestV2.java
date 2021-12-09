import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BankSystemTestV2 {

    private CardsManagementSystem system = new CardsManagementSystem(null);

    @Test
    @DisplayName("카드 추가 테스트")
    void createCardTest() throws Exception {
        //given
        Card card = system.addCard();
        //when
        assertEquals(16, card.getCardNumber().length());
        assertEquals(4, card.getPinNumber().length());
    }


    @Test
    @DisplayName("카드 찾기 테스트")
    void findAccountTest() throws Exception {
        //given
        setupAccount();
        //when
        Card card = system.addCard();
        Card findCard = system.findAccount(card.getCardNumber(), card.getPinNumber());

        System.out.println("findCard = " + findCard);

        List<Card> cardList = system.getCardList();

        Card resultCard = cardList.stream().filter(
                        account -> account.getPinNumber().equals(findCard.getPinNumber())
                                && account.getCardNumber().equals(findCard.getCardNumber())
                ).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        //then
        assertNotNull(resultCard);
    }


    @Test
    @DisplayName("카드 찾기 테스트 실패 ")
    void findAccountTest_fail() throws Exception {
        //given
        List<Card> cardList = system.getCardList();

        //then
        assertThrows(IllegalArgumentException.class, () -> {
             cardList.stream().filter(
                            account -> account.getPinNumber().equals("111")
                                    && account.getCardNumber().equals("2222")
                    ).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        });
    }

    private void setupAccount() throws SQLException {
        for (int i = 0; i < 3; i++) {
            system.addCard();
        }
    }
}
