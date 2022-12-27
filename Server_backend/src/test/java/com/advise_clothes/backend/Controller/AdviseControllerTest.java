package com.advise_clothes.backend.Controller;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.request.AdviseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

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
        AdviseRequest request = AdviseRequest.builder()
                .temperature(10)
                .weather("rain")
                .build();

        // expected
        mockMvc.perform(get("/api/advise")
                        .params(objectToParams(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/api/advise GET 요청 시 날씨가 없을 때")
    void getAdviseWithoutWeather() throws Exception {
        // given
        int temperature = 10;

        // expected
        mockMvc.perform(get("/api/advise")
                        .param("temperature", String.valueOf(temperature)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.fieldName").value("weather"))
                .andExpect(jsonPath("$.validation.message").value("날씨를 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("/api/advise GET 요청 시 기온이 없을 때")
    void getAdviseWithoutTemperature() throws Exception {
        // given
        String weather = "rain";

        // expected
        mockMvc.perform(get("/api/advise")
                        .param("weather", weather))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.fieldName").value("temperature"))
                .andExpect(jsonPath("$.validation.message").value("기온을 입력해주세요."))
                .andDo(print());
    }
}