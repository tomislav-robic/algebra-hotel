package robic.tomislav.algebrahotel.algebrahotel.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import robic.tomislav.algebrahotel.algebrahotel.domain.RoomType;
import robic.tomislav.algebrahotel.algebrahotel.repository.JpaRoomTypeRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("api/room-type")
public class RoomTypeRestController {

    private final JpaRoomTypeRepository jpaRoomTypeRepository;

    public RoomTypeRestController(JpaRoomTypeRepository jpaRoomTypeRepository) {
        this.jpaRoomTypeRepository = jpaRoomTypeRepository;
    }

    @GetMapping
    public Iterable<RoomType> findAll() {
        return jpaRoomTypeRepository.findAll();
    }

    @GetMapping("{id}")
    public RoomType findOne(@PathVariable Long id) {
        return jpaRoomTypeRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room type was not found")
                );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RoomType save(@Valid @RequestBody RoomType roomType) {
        if(roomType.getId() != null) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room type ID must be left empty when creating room type");
        }

        return jpaRoomTypeRepository.save(roomType);
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if(!jpaRoomTypeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Room type with provided ID does not exist");
        }
        jpaRoomTypeRepository.deleteById(id);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("{id}/update-price")
    public RoomType updatePrice(@PathVariable Long id, @RequestParam float price) {
        final RoomType roomType = jpaRoomTypeRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.CONFLICT, "Room type with provided ID does not exist")
                );

        roomType.setPrice(price);

        return jpaRoomTypeRepository.save(roomType);
    }

}
