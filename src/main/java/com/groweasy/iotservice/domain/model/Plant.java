package com.groweasy.iotservice.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.xml.transform.Source;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "plants")
public class Plant  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "max_temperature", nullable = false)
    private double maxTemperature;

    @NotNull
    @Column(name = "min_temperature",  nullable = false)
    private double minTemperature;

    @NotNull
    @Column(name = "max_humidity", nullable = false)
    private double maxHumidity;

    @Column(name = "min_humidity", nullable = false)
    private double minHumidity;


    @Column(name="user_id", nullable = false)
    private String userId;



    @JsonIgnore
    @OneToOne(mappedBy = "plant")
    private Device device;

    @JsonIgnore
    @OneToOne(mappedBy = "plant")
    private Project project;
}
