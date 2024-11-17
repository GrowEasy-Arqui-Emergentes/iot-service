package com.groweasy.iotservice.domain.service;

import com.groweasy.iotservice.domain.model.Project;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectService {

    List<Project> getProjectsByUserId(String userId);
    Project getById(Long id);
    Project create(Project project, Long idPlant, Long idDevice);
    Project update(Long id, Project project);
    ResponseEntity<?> deleteProject(Long id);
}
