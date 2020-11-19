package robic.tomislav.algebrahotel.algebrahotel.repository;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import robic.tomislav.algebrahotel.algebrahotel.domain.RoomType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcRoomTypeRepository implements RoomTypeRepository {

    private static final String TABLE_NAME = "room_type";
    private static final String GENERATED_KEY_COLUMN = "id";

    private static final  String SELECT_ALL = "SELECT * FROM room_type";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert roomTypeInserter;

    public JdbcRoomTypeRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.roomTypeInserter = new SimpleJdbcInsert(jdbc)
            .withTableName(TABLE_NAME)
            .usingGeneratedKeyColumns(GENERATED_KEY_COLUMN);
    }

    @Override
    public Set<RoomType> findAll() {
        return Set.copyOf(jdbc.query(SELECT_ALL, this::mapRowsToRoomType));
    }

    @Override
    public Optional<RoomType> save(RoomType roomType) {
        try {
            roomType.setId(saveRoomTypeDetails(roomType));
            return Optional.of(roomType);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }

    private RoomType mapRowsToRoomType(ResultSet rs, int rowNum) throws SQLException {
        return new RoomType(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("numberOfBeds"),
                rs.getFloat("price"),
                rs.getString("shortDescription")
        );
    }

    private Long saveRoomTypeDetails(RoomType roomType) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", roomType.getName());
        values.put("numberOfBeds", roomType.getNumberOfBeds());
        values.put("price", roomType.getPrice());
        values.put("shortDescription", roomType.getShortDescription());

        return roomTypeInserter.executeAndReturnKey(values).longValue();
    }
}
