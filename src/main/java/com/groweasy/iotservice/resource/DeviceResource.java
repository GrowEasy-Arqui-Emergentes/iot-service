package com.groweasy.iotservice.resource;

import com.groweasy.iotservice.domain.model.Plant;
import com.groweasy.iotservice.domain.model.Project;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResource {
    private Long id;
    private String name;
    private String description;
    private double temperature;
    private double humidity;
    private String userId;
    private PlantResponse plant;
    private ProjectResponse project;
}
