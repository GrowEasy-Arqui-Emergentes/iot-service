package com.groweasy.iotservice.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PlantResponse {
    private Long id;
    private String name;
    private String description;
    private double maxTemperature;
    private double minTemperature;
    private double maxHumidity;
    private double minHumidity;
    private String userId;
}
