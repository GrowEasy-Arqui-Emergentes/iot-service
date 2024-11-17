package com.groweasy.iotservice.domain.service;

import com.groweasy.iotservice.domain.model.Device;
import com.groweasy.iotservice.domain.model.Plant;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlantService {
    List<Plant> getPlantsByUserId(String userId);
    Plant getPlantByNameAndUserId(String Name, String userId);
    Plant getPlantById(Long id);
    Plant create(Plant plant);
    Plant update(Long id, Plant plant);
    ResponseEntity<?> deletePlant(Long id);
}
