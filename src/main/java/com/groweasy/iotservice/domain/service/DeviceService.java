package com.groweasy.iotservice.domain.service;

import com.groweasy.iotservice.domain.model.Device;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeviceService {
    Device getDeviceById(Long id);
    List<Device> getDevicesByUserId(String userId);
    Device create(Device device, Long idPlant);
    Device update(Long id, Device device);
    Device patchDeviceTemperature(Long id, double temperature);
    Device patchDeviceHumidity(Long id, double humidity);
    ResponseEntity<?> deleteDevice(Long id);

}
