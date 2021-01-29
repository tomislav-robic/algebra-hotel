package robic.tomislav.algebrahotel.algebrahotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import robic.tomislav.algebrahotel.algebrahotel.domain.RoomType;
import robic.tomislav.algebrahotel.algebrahotel.domain.RoomTypeSearch;
import robic.tomislav.algebrahotel.algebrahotel.service.RoomTypeService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("room-type")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @GetMapping("list")
    public String roomTypeList(Model model) {
        List<RoomType> roomTypes = roomTypeService.findAll();
        model.addAttribute("roomTypes", roomTypes);
        return "room-type/room-type-list";
    }

    @GetMapping("new")
    public String roomCreate (Model model) {
        model.addAttribute("roomType", new RoomType());
        return "room-type/room-type-form";
    }

    @PostMapping("new")
    public String roomCreated(@Valid RoomType roomType, Errors errors){
        if(errors.hasErrors()) {
            return "room-type/room-type-form";
        }

        roomTypeService.save(roomType);

        return "room-type/room-type-created";
    }

    @GetMapping("search")
    public String roomSearch (Model model) {
        model.addAttribute("roomTypeSearch", new RoomTypeSearch());
        return "room-type/room-type-search";
    }

    @PostMapping("search")
    public String roomSearchResult(@Valid RoomTypeSearch roomTypeSearch, Errors errors, Model model){
        if(errors.hasErrors()) {
            return "room-type/room-type-search";
        }

        List<RoomType> roomTypes = new ArrayList<RoomType>(roomTypeService.findByEnteredQueryData(roomTypeSearch));
        model.addAttribute("roomTypes", roomTypes);
        model.addAttribute("roomTypeSearch", roomTypeSearch);

        return "room-type/room-type-search-result";
    }
}
