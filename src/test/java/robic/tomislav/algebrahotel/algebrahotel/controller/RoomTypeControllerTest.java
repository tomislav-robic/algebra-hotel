package robic.tomislav.algebrahotel.algebrahotel.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void invalidRoomTypeSubmit() throws Exception {
        this.mockMvc
                .perform(
                        post("/room-type/new")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .with(csrf())
                            .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("room-type/room-type-form"));
    }

    @Test
    @DirtiesContext
    void validRoomTypeSubmit() throws Exception {
        this.mockMvc
                .perform(
                        post("/room-type/new")
                                .param("name", "Test Room Type")
                                .param("numberOfBeds", "1")
                                .param("price", "123.12")
                                .param("shortDescription", "short description")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("room-type/room-type-created"));
    }

    @Test
    void searchRoomTypeSubmit() throws Exception {
        this.mockMvc
                .perform(
                        post("/room-type/search")
                                .param("priceFrom", "100")
                                .param("priceTo", "130")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("room-type/room-type-search-result"))
                .andExpect(model().attributeExists("roomTypes"))
                .andExpect(model().attributeExists("roomTypeSearch"))
                .andExpect(model().attribute("roomTypes", hasSize(0)));
    }

}
