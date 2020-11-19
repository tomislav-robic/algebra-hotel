package robic.tomislav.algebrahotel.algebrahotel.repository;

import robic.tomislav.algebrahotel.algebrahotel.domain.RoomType;

import java.util.Optional;
import java.util.Set;

public interface RoomTypeRepository {

    Set<RoomType> findAll();

    Optional<RoomType> save (RoomType roomType);
}
