package com.groweasy.iotservice.resource;

import com.groweasy.iotservice.domain.model.Device;
import com.groweasy.iotservice.domain.model.Project;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PlantResource {
    private Long id;
    private String name;
    private String description;
    private double maxTemperature;
    private double minTemperature;
    private double maxHumidity;
    private double minHumidity;
    private String userId;
    private DeviceResponse device;
    private ProjectResponse project;

}
