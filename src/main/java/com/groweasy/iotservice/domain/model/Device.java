package com.groweasy.iotservice.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "devices")
public class Device {
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
    @Column(name = "temperature", nullable = false)
    private double temperature;

    @NotNull
    @Column(name = "humidity", nullable = false)
    private double humidity;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userId;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "plant_id", referencedColumnName = "id")
    private Plant plant;

    @JsonIgnore
    @OneToOne(mappedBy = "device")
    private Project project;
}
