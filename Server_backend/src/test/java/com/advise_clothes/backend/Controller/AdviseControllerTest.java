package com.advise_clothes.backend.Controller;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
class AdviseControllerTest extends ServerBackendApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAdvise() throws Exception {
        // given
        String temperature = "10";
        String weather = "rain";

        // expected
        mockMvc.perform(get("/api/advise?temperature=" + temperature + "&weather=" + weather))
                .andExpect(status().isOk())
                .andDo(print());

    }

}