package com.advise_clothes.backend.Controller;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.repository.SessionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class SessionControllerTest extends ServerBackendApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    @DisplayName("/api/session/{sessionKey} GET 요청 시 세션 조회")
    void read() throws Exception {
        // given
        String sessionKey = sessionRepository.findById(19L).get().getSessionKey();

        // excepted
        mockMvc.perform(get("/api/session/{sessionKey}", sessionKey))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sessionKey").value(sessionKey))
                .andDo(print());
    }

    /**
     * SessionService.create()를 사용하고 있기 때문에 작동이 안할 거라 판단, 더 작성하지 않음.
     * @throws Exception
     */
    @Test
    @DisplayName("/api/session POST 요청 시 세션 생성")
    void create() throws Exception {
    }

    @Test
    @DisplayName("/api/session/{sessionKey} DELETE 요청 시 세션 삭제")
    @Transactional
    void deleteSession() throws Exception {
        // given
        String sessionKey = sessionRepository.findById(25L)
                .orElseThrow(() -> new RuntimeException("세션이 없습니다."))
                .getSessionKey();

        // excepted
        mockMvc.perform(delete("/api/session/{sessionKey}", sessionKey))
                .andExpect(status().isOk())
                .andDo(print());
    }

}