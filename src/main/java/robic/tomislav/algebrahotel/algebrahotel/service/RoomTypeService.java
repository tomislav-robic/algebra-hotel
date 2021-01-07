package robic.tomislav.algebrahotel.algebrahotel.service;

import org.springframework.stereotype.Service;
import robic.tomislav.algebrahotel.algebrahotel.domain.RoomType;
import robic.tomislav.algebrahotel.algebrahotel.domain.RoomTypeSearch;
import robic.tomislav.algebrahotel.algebrahotel.repository.JpaRoomTypeRepository;
import robic.tomislav.algebrahotel.algebrahotel.repository.RoomTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;
    private final JpaRoomTypeRepository jpaRoomTypeRepository;


    public RoomTypeService(RoomTypeRepository roomTypeRepository, JpaRoomTypeRepository jpaRoomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
        this.jpaRoomTypeRepository = jpaRoomTypeRepository;
    }

    /*
    public Optional<RoomType> save (RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }
     */


    public RoomType save (RoomType roomType) {
        return jpaRoomTypeRepository.save(roomType);
    }

    
    public  List<RoomType> findAll() {
        return jpaRoomTypeRepository.findAll();
    }

    public List<RoomType> findByEnteredQueryData(RoomTypeSearch roomTypeSearch) {
        return jpaRoomTypeRepository.findAllByPrice(roomTypeSearch.getPriceFrom(), roomTypeSearch.getPriceTo());
    }
}
