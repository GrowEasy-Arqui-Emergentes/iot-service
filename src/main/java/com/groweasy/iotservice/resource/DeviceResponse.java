package com.groweasy.iotservice.resource;

import com.groweasy.iotservice.domain.model.Plant;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResponse {
    private Long id;
    private String name;
    private String description;
    private double temperature;
    private double humidity;
    private String userId;
}
