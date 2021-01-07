package robic.tomislav.algebrahotel.algebrahotel.service;

import org.springframework.stereotype.Service;
import robic.tomislav.algebrahotel.algebrahotel.domain.RoomType;
import robic.tomislav.algebrahotel.algebrahotel.domain.RoomTypeSearch;
import robic.tomislav.algebrahotel.algebrahotel.repository.JpaRoomTypeRepository;

import java.util.List;

@Service
public class RoomTypeService {
    private final JpaRoomTypeRepository jpaRoomTypeRepository;


    public RoomTypeService(JpaRoomTypeRepository jpaRoomTypeRepository) {
        this.jpaRoomTypeRepository = jpaRoomTypeRepository;
    }

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
