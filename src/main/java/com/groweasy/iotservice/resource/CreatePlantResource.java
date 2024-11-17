package com.groweasy.iotservice.resource;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlantResource implements Serializable {
    private String name;
    private String description;
    private double maxTemperature;
    private double minTemperature;
    private double maxHumidity;
    private double minHumidity;
    private String userId;
}
