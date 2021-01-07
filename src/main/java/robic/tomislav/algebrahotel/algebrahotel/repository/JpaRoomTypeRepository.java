package robic.tomislav.algebrahotel.algebrahotel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import robic.tomislav.algebrahotel.algebrahotel.domain.RoomType;
import robic.tomislav.algebrahotel.algebrahotel.domain.RoomTypeSearch;

import java.util.List;
import java.util.Set;

public interface JpaRoomTypeRepository extends CrudRepository<RoomType, Long> {

    List<RoomType> findAll();

    Set<RoomType> findAllByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT * FROM room_type WHERE price >= ?1 AND price <= ?2", nativeQuery = true)
    List<RoomType> findAllByPrice(float priceFrom, float priceTo);
}
