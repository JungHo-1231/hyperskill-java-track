import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardsManagementSystem {
    private CardDao cardDao;

    public CardsManagementSystem(DataSource dataSource) {
        cardDao = new CardDao(dataSource);
    }

    public Card addCard() throws SQLException {
        return cardDao.addCard(new Card());
    }

    public Card findAccount(String cardNumber, String cardPin) {
        return null;
    }

    public List<Card> getCardList() {
        return null;
    }
}
