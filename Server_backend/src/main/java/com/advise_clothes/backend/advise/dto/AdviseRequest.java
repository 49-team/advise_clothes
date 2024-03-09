package com.advise_clothes.backend.advise.dto;


import com.advise_clothes.backend.exception.InvalidRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdviseRequest {

//    @NotBlank(message = "기온을 입력해주세요.")
    private Integer temperature;

//    @NotBlank(message = "날씨를 입력해주세요.")
    private String weather;

    @Builder
    public AdviseRequest(Integer temperature, String weather) {
        this.temperature = temperature;
        this.weather = weather;
        this.validate();
    }

    public void validate() {
        if (this.temperature == null) {
            throw new InvalidRequest("temperature", "기온을 입력해주세요.");
        }
        if (this.weather == null) {
            throw new InvalidRequest("weather", "날씨를 입력해주세요.");
        }
    }
}
