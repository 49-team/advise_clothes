package com.advise_clothes.backend.domain.business;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClothesAdvise {

    private int temperature;
    private String weather;

    @Builder
    public ClothesAdvise(int temperature, String weather) {
        this.temperature = temperature;
        this.weather = weather;
    }

    public List<Clothes> advice
}
