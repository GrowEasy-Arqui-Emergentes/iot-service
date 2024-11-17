package com.groweasy.iotservice.service;

import com.groweasy.iotservice.domain.model.Device;
import com.groweasy.iotservice.domain.model.Plant;
import com.groweasy.iotservice.domain.persistence.DeviceRepository;
import com.groweasy.iotservice.domain.persistence.PlantRepository;
import com.groweasy.iotservice.domain.service.DeviceService;
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
public class DeviceServiceImpl implements DeviceService {
    private static final String ENTITY = "Device";

    final private DeviceRepository deviceRepository;
    final private PlantRepository plantRepository;
    final private Validator validator;
    public DeviceServiceImpl(DeviceRepository deviceRepository, PlantRepository plantRepository, Validator validator) {
        this.deviceRepository = deviceRepository;
        this.plantRepository = plantRepository;
        this.validator = validator;
    }

    @Override
    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device with id " + id + " not found"));
    }

    @Override
    public List<Device> getDevicesByUserId(String userId) {
        List<Device> devicesByUser = deviceRepository.findAllByUserId(userId);
        if(devicesByUser.isEmpty()){
           throw new ResourceNotFoundException("No devices found for user " + userId);
        }else{
            return devicesByUser;
        }
    }

    @Override
    public Device create(Device device, Long idPlant) {
        Set<ConstraintViolation<Device>> violations = validator.validate(device);
        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }
        device.setId(null);
        Optional<Plant> plantOptional = plantRepository.findById(idPlant);
        if(plantOptional.isEmpty()) {
            throw new ResourceValidationException("Plant with id " + idPlant + " not found, not possible to create new Device");
        }else{
            device.setPlant(plantOptional.get());
            plantOptional.get().setDevice(device);
            return deviceRepository.save(device);
        }
    }

    @Override
    public Device update(Long id, Device device) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if(deviceOptional.isPresent()) {
            device.setPlant(deviceOptional.get().getPlant());
            device.setId(id);
            return deviceRepository.save(device);
        }else{
            throw new ResourceNotFoundException("Device with id " + id + " not found");
        }
    }

    @Override
    public Device patchDeviceTemperature(Long id, double temperature) {
        Optional<Device> deviceExist = deviceRepository.findById(id);
        if(deviceExist.isPresent()) {
            Device device = deviceExist.get();
            device.setTemperature(temperature);
            return deviceRepository.save(device);
        }else{
            throw new ResourceNotFoundException("Device with id " + id + " not found");
        }
    }

    @Override
    public Device patchDeviceHumidity(Long id, double humidity) {
        Optional<Device> deviceExist = deviceRepository.findById(id);
        if(deviceExist.isPresent()) {
            Device device = deviceExist.get();
            device.setHumidity(humidity);
            return deviceRepository.save(device);
        }else{
            throw new ResourceNotFoundException("Device with id " + id + " not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteDevice(Long id) {
        return deviceRepository.findById(id).map(device ->{
            deviceRepository.delete(device);
            return ResponseEntity.ok().build();}).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
