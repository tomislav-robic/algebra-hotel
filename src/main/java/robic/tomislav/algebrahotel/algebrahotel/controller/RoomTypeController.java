package robic.tomislav.algebrahotel.algebrahotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import robic.tomislav.algebrahotel.algebrahotel.domain.RoomType;
import robic.tomislav.algebrahotel.algebrahotel.repository.RoomTypeRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("room-type")
public class RoomTypeController {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeController(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @GetMapping("list")
    public String roomTypeList(Model model) {
        List<RoomType> roomTypes = new ArrayList<RoomType>(roomTypeRepository.findAll());
        model.addAttribute("roomTypes", roomTypes);
        return "room-type/room-type-list";
    }

    @GetMapping("new")
    public String roomCreate (Model model) {
        model.addAttribute("roomType", new RoomType());
        return "room-type/room-type-form";
    }

    @PostMapping("created")
    public String roomCreated(@Valid RoomType roomType, Errors errors){
        if(errors.hasErrors()) {
            return "room-type/room-type-form";
        }

        roomTypeRepository.save(roomType);

        return "room-type/room-type-created";
    }
}
