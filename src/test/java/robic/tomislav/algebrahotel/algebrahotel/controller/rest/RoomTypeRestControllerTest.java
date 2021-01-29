package robic.tomislav.algebrahotel.algebrahotel.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import robic.tomislav.algebrahotel.algebrahotel.domain.RoomType;
import robic.tomislav.algebrahotel.algebrahotel.repository.JpaRoomTypeRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomTypeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JpaRoomTypeRepository roomTypeRepository;

    @Test
    void getAllRoomTypes() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/room-type")
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DirtiesContext
    void saveRoomTypes() throws Exception {
        String TEST_NAME = "Test Room Type";
        int TEST_NUMBER_OF_BEDS = 3;
        float TEST_PRICE = (float) 315.76;
        String TEST_SHORT_DESCRIPTION = "short 3";

        RoomType roomType = new RoomType();
        roomType.setName(TEST_NAME);
        roomType.setNumberOfBeds(TEST_NUMBER_OF_BEDS);
        roomType.setPrice(TEST_PRICE);
        roomType.setShortDescription(TEST_SHORT_DESCRIPTION);

        this.mockMvc
                .perform(
                        post("/api/room-type")
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomType))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.price").value(TEST_PRICE));
    }

    @Test
    @DirtiesContext
    void deleteRoomTypes() throws Exception {
        final long TEST_DELETE_ROOM_TYPE_ID = 1L;

        String TEST_NAME = "Test Room Type";
        int TEST_NUMBER_OF_BEDS = 3;
        float TEST_PRICE = (float) 315.76;
        String TEST_SHORT_DESCRIPTION = "short 3";

        RoomType roomType = new RoomType();
        roomType.setId(TEST_DELETE_ROOM_TYPE_ID);
        roomType.setName(TEST_NAME);
        roomType.setNumberOfBeds(TEST_NUMBER_OF_BEDS);
        roomType.setPrice(TEST_PRICE);
        roomType.setShortDescription(TEST_SHORT_DESCRIPTION);

        roomTypeRepository.save(roomType);

        assertTrue(roomTypeRepository.findById(TEST_DELETE_ROOM_TYPE_ID).isPresent());

        this.mockMvc
                .perform(
                        delete("/api/room-type/" + TEST_DELETE_ROOM_TYPE_ID)
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                )
                .andExpect(status().isNoContent());

        assertTrue(roomTypeRepository.findById(TEST_DELETE_ROOM_TYPE_ID).isEmpty());
    }

    @Test
    @DirtiesContext
    void deleteRoomTypesWithoutAdminRights() throws Exception {
        final long TEST_DELETE_ROOM_TYPE_ID = 1L;

        String TEST_NAME = "Test Room Type";
        int TEST_NUMBER_OF_BEDS = 3;
        float TEST_PRICE = (float) 315.76;
        String TEST_SHORT_DESCRIPTION = "short 3";

        RoomType roomType = new RoomType();
        roomType.setId(TEST_DELETE_ROOM_TYPE_ID);
        roomType.setName(TEST_NAME);
        roomType.setNumberOfBeds(TEST_NUMBER_OF_BEDS);
        roomType.setPrice(TEST_PRICE);
        roomType.setShortDescription(TEST_SHORT_DESCRIPTION);

        roomTypeRepository.save(roomType);

        this.mockMvc
                .perform(
                        delete("/api/room-type/" + TEST_DELETE_ROOM_TYPE_ID)
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("USER"))
                )
                .andExpect(status().isForbidden());

    }
}
