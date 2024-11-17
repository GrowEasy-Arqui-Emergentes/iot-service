package com.groweasy.iotservice.service;

import com.groweasy.iotservice.domain.model.Device;
import com.groweasy.iotservice.domain.model.Plant;
import com.groweasy.iotservice.domain.model.Project;
import com.groweasy.iotservice.domain.persistence.DeviceRepository;
import com.groweasy.iotservice.domain.persistence.PlantRepository;
import com.groweasy.iotservice.domain.persistence.ProjectRepository;
import com.groweasy.iotservice.domain.service.ProjectService;
import com.groweasy.iotservice.shared.exception.ResourceNotFoundException;
import com.groweasy.iotservice.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {
    private static final String ENTITY = "Project";

    private final ProjectRepository projectRepository;
    private final Validator validator;
    private final PlantRepository plantRepository;
    private final DeviceRepository deviceRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, Validator validator, PlantRepository plantRepository, DeviceRepository deviceRepository) {
        this.projectRepository = projectRepository;
        this.validator = validator;
        this.plantRepository = plantRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Project> getProjectsByUserId(String userId) {
        List<Project> projects = projectRepository.findAllByUserId(userId);
        if(projects.isEmpty()){
            throw new ResourceNotFoundException("No projects found for user id " + userId);
        }else{
            return projects;
        }
        /*return projects.stream().filter(project ->project.getUserId().equals(userId)).toList();*/
    }

    @Override
    public Project getById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Project create(Project project, Long idPlant, Long idDevice) {
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        project.setId(null);
        Plant plant = plantRepository.findById(idPlant).orElseThrow(() -> new ResourceNotFoundException("Plant", idPlant));
        if(plant != null){
            project.setPlant(plant);
            plant.setProject(project);
        }
        Device device = deviceRepository.findById(idDevice).orElseThrow(() -> new ResourceNotFoundException("Device", idDevice));
        if(device != null){
            project.setDevice(device);
            device.setProject(project);
        }
        return projectRepository.save(project);

    }

    @Override
    public Project update(Long id, Project project) {
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }else {
            Optional<Project> _project = projectRepository.findById(id);
            if (_project.isEmpty()) {
                throw new ResourceNotFoundException(ENTITY, id);
            } else {
                project.setCreatedAt(_project.get().getCreatedAt());
                project.setPlant(_project.get().getPlant());
                project.setDevice(_project.get().getDevice());
                project.setId(id);
                return projectRepository.save(project);
            }
        }
    }

    @Override
    public ResponseEntity<?> deleteProject(Long id) {
        return projectRepository.findById(id).map(project ->{
            projectRepository.delete(project);
            return ResponseEntity.ok().build();}).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
