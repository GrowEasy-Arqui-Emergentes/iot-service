package com.groweasy.iotservice.api.rest;

import com.groweasy.iotservice.domain.service.PlantService;
import com.groweasy.iotservice.mapping.PlantMapper;
import com.groweasy.iotservice.resource.CreatePlantResource;
import com.groweasy.iotservice.resource.PlantResource;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/iot-service/plants")
public class PlantController {
    private final PlantService plantService;
    private final PlantMapper plantMapper;

    public PlantController(PlantService plantService, PlantMapper plantMapper) {
        this.plantService = plantService;
        this.plantMapper = plantMapper;
    }

    @Operation(summary = "Get plant by Id")
    @GetMapping("{idPlant}")
    public PlantResource getPlantById(@PathVariable("idPlant") Long idPlant) {
        return plantMapper.toResource(plantService.getPlantById(idPlant));
    }

    @Operation(summary = "Create new plant")
    @PostMapping("/generate")
    public ResponseEntity<PlantResource> generatePlant(@RequestBody CreatePlantResource createPlantResource) {
        return new ResponseEntity<>(plantMapper.toResource(plantService.create(plantMapper.toModel(createPlantResource))), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing plant")
    @PutMapping("/update/{plantId}")
    public ResponseEntity<PlantResource> updatePlant(@PathVariable("plantId") Long plantId, @RequestBody CreatePlantResource createPlantResource) {
        return new ResponseEntity<>(plantMapper.toResource(plantService.update(plantId, plantMapper.toModel(createPlantResource))), HttpStatus.OK);
    }

    @Operation(summary = "Delete a plant")
    @DeleteMapping("/delete/{plantId}")
    public ResponseEntity<?> deletePlant(@PathVariable("plantId") Long plantId) {
        return plantService.deletePlant( plantId);
    }

    @Operation(summary = "Get all plants by userId")
    @GetMapping("/user/{userId}")
    public List<PlantResource> getAllPlants(@PathVariable("userId") String userId) {
        return plantService.getPlantsByUserId(userId).stream().map(plantMapper::toResource).toList();
    }

    @Operation(summary = "Get plant by its name and userId")
    @GetMapping("/name/{plantName}/{userId}")
    public PlantResource getPlantByNameAndUserId(@PathVariable("plantName") String plantName, @PathVariable("userId") String userId) {
        return plantMapper.toResource(plantService.getPlantByNameAndUserId(plantName, userId));
    }
}
