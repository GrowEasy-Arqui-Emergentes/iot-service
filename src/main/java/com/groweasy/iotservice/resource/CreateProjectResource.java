package com.groweasy.iotservice.resource;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectResource {
    private String name;
    private String description;
    private String userId;
}
