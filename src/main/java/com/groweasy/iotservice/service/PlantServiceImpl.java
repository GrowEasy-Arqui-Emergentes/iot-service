package com.groweasy.iotservice.service;

import com.groweasy.iotservice.domain.model.Device;
import com.groweasy.iotservice.domain.model.Plant;
import com.groweasy.iotservice.domain.model.Project;
import com.groweasy.iotservice.domain.persistence.PlantRepository;
import com.groweasy.iotservice.domain.service.PlantService;
import com.groweasy.iotservice.shared.exception.ResourceNotFoundException;
import com.groweasy.iotservice.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlantServiceImpl implements PlantService {
    private static final String ENTITY = "Plant";

    private final PlantRepository plantRepository;
    private final Validator validator;

    public PlantServiceImpl(PlantRepository plantRepository, Validator validator) {
        this.plantRepository = plantRepository;
        this.validator = validator;
    }

    @Override
    public List<Plant> getPlantsByUserId(String userId) {
        List<Plant> plants = plantRepository.findAllByUserId(userId);
        if(plants.isEmpty()){
            throw new ResourceNotFoundException("No plant found for user id " + userId);
        }else{
            return plants;
        }
        /*
        List<Plant> plants = plantRepository.findAll();
        return plants.stream().filter(plant ->
            plant.getUserId().equals(userId)).toList();*/
    }

    @Override
    public Plant getPlantByNameAndUserId(String plantName, String userId) {
        return plantRepository.findByNameAndUserId(plantName, userId);
    }

    @Override
    public Plant getPlantById(Long id) {
        return plantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plant with id " + id + " not found!"));
    }

    @Override
    public Plant create(Plant plant) {
        Set<ConstraintViolation<Plant>> violations = validator.validate(plant);
        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        plant.setId(null);
        List<Plant> plantsByUser = getPlantsByUserId(plant.getUserId());
        if(plantsByUser.isEmpty()) {
            return plantRepository.save(plant);
        }else{
            List<Plant> plantsWithName = plantsByUser.stream().filter(_plant -> _plant.getName().equals(plant.getName())).toList();
            if(plantsWithName.isEmpty()) {
                return plantRepository.save(plant);
            }else{
                throw new ResourceValidationException("You have already a " + ENTITY + " with the same name", plant.getName());
            }
        }
    }

    @Override
    public Plant update(Long id, Plant plant) {
        Optional<Plant> _plant = plantRepository.findById(id);
        if (_plant.isPresent()) {
            plant.setId(id);
            return plantRepository.save(plant);
        }else{
            return null;
        }
    }

    @Override
    public ResponseEntity<?> deletePlant(Long id) {
        return plantRepository.findById(id).map(plant ->{
            plantRepository.delete(plant);
            return ResponseEntity.ok().build();}).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
