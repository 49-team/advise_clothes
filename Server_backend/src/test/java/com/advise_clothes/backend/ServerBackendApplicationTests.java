package com.advise_clothes.backend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@SpringBootTest
public class ServerBackendApplicationTests {

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    /**
     * HTTP GET의 파라미터를 쉽게 보내기 위해 만든 메서드.
     * MockMvcRequestBuilders의 메서드 params가 파라미터로 MultiValueMap<String, String>로 받는다.
     * @param object MultiValueMap으로 바꿀 Object
     * @return MultiValueMap으로 변환 된 Object
     */
    protected MultiValueMap<String, String> objectToParams(Object object) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        Map<String, String> map = objectMapper.convertValue(object, new TypeReference<Map<String, String>>() {});
        params.setAll(map);

        return params;
    }
}
