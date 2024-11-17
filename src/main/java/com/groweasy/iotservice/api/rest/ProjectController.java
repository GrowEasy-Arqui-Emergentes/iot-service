package com.groweasy.iotservice.api.rest;


import com.groweasy.iotservice.domain.service.ProjectService;
import com.groweasy.iotservice.mapping.ProjectMapper;
import com.groweasy.iotservice.resource.CreateProjectResource;
import com.groweasy.iotservice.resource.ProjectResource;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/iot-service/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @Operation(summary = "Get project by id")
    @GetMapping("{projectId}")
    public ProjectResource getProjectById(@PathVariable Long projectId) {
        return projectMapper.toResource(projectService.getById(projectId));
    }

    @Operation(summary = "Get all projects by userId")
    @GetMapping("/user/{userId}")
    public List<ProjectResource> getProjectsByUserId(@PathVariable("userId") String userId) {
        return projectService.getProjectsByUserId(userId).stream().map(projectMapper::toResource).toList();
    }

    @Operation(summary = "Generate a new project with the deviceId and the plantId")
    @PostMapping("/generate/{deviceId}/{plantId}")
    public ResponseEntity<ProjectResource> generateProject(@RequestBody CreateProjectResource createProjectResource, @PathVariable("deviceId") Long deviceId, @PathVariable("plantId") Long plantId) {
        return new ResponseEntity<>(projectMapper.toResource(projectService.create(projectMapper.toModel(createProjectResource),plantId,deviceId)), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a project by Id")
    @PutMapping("/update/{projectId}")
    public ResponseEntity<ProjectResource> updateProject(@RequestBody CreateProjectResource createProjectResource, @PathVariable("projectId") Long projectId) {
        return new ResponseEntity<>(projectMapper.toResource(projectService.update(projectId,projectMapper.toModel(createProjectResource))), HttpStatus.OK);
    }

    @Operation(summary = "Delete a project by Id ")
    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable("projectId") Long projectId){
        return projectService.deleteProject(projectId);
    }




}
