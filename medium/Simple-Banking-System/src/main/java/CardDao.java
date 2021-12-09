
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CardDao {
    private final DataSource dataSource;
    private final JdbcContext jdbcContext;
    private final JdbcTemplate jdbcTemplate;
    private RowMapper<Card> userMapper
            = (rs, rowNum) -> new Card(
            rs.getString("number"),
            rs.getString("pin"),
            rs.getInt("balance")
    );

    public CardDao(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcContext = new JdbcContext(dataSource);
        jdbcTemplate = new JdbcTemplate();
    }

    public Card addCard(Card card) throws SQLException {
        jdbcContext.workWithStatementStrategy(c -> {
            PreparedStatement preparedStatement = c.prepareStatement("insert into card value (?, ?)");
            preparedStatement.setString(1, card.getCardNumber());
            preparedStatement.setString(2, card.getPinNumber());
            return preparedStatement;
        });
        return card;
    }

    public void addIncome(Card card, int incomeToUpdate) throws SQLException {
        jdbcContext.workWithStatementStrategy(c -> {
            PreparedStatement preparedStatement = c.prepareStatement("update card set balance =? where number =? and pin = ?");
            preparedStatement.setString(1, String.valueOf(card.getBalance()));
            preparedStatement.setString(2, card.getCardNumber());
            preparedStatement.setString(3, card.getPinNumber());
            return preparedStatement;
        });
    }

    public void findAccount(Card card) {

    }

    public void deleteAccount(Card card) throws SQLException {
        jdbcContext.workWithStatementStrategy(c -> {
            PreparedStatement preparedStatement = c.prepareStatement("delete card where number =? ");
            preparedStatement.setString(1, card.getCardNumber());
            return preparedStatement;
        });
    }
}
