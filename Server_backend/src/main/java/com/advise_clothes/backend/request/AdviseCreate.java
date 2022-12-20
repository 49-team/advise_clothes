package com.advise_clothes.backend.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AdviseCreate {

    @NotBlank(message = "기온을 입력해주세요.")
    private String temperature;

    @NotBlank(message = "날씨를 입력해주세요.")
    private String weather;

    @Builder
    public AdviseCreate(String temperature, String weather) {
        this.temperature = temperature;
        this.weather = weather;
    }
}
