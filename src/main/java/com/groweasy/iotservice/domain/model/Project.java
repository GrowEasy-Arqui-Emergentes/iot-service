package com.groweasy.iotservice.domain.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="name", nullable = false)
    private String name;

    @NotNull
    @Column(name="description", nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="user_id", nullable = false)
    private String userId;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private Device device;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "plant_id", referencedColumnName = "id")
    private Plant plant;
}
