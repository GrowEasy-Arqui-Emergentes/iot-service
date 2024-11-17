package com.groweasy.iotservice.resource;

import com.groweasy.iotservice.domain.model.Device;
import com.groweasy.iotservice.domain.model.Project;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateDeviceResource {
    private String name;
    private String description;
    private double temperature;
    private double humidity;
    private String userId;
}
