package com.groweasy.iotservice.api.rest;


import com.groweasy.iotservice.domain.service.DeviceService;
import com.groweasy.iotservice.mapping.DeviceMapper;
import com.groweasy.iotservice.resource.CreateDeviceResource;
import com.groweasy.iotservice.resource.DeviceResource;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/iot-service/devices")
public class DeviceController {
    private final DeviceService deviceService;
    private final DeviceMapper deviceMapper;

    public DeviceController(DeviceService deviceService, DeviceMapper deviceMapper) {
        this.deviceService = deviceService;
        this.deviceMapper = deviceMapper;
    }

    @Operation(summary = "Get device by Id")
    @GetMapping("{deviceId}")
    public DeviceResource getDevice(@PathVariable("deviceId") Long deviceId) {
        return deviceMapper.toResource(deviceService.getDeviceById(deviceId));
    }

    @Operation(summary = "Get all devices from a user")
    @GetMapping("/user/{userId}")
    public List<DeviceResource> getAllDevices(@PathVariable("userId") String userId) {
        return deviceService.getDevicesByUserId(userId).stream().map(deviceMapper::toResource).toList();
    }

    @Operation(summary = "Create new device with its plant assigned")
    @PostMapping("/generate/{plantId}")
    public ResponseEntity<DeviceResource> createDevice(@PathVariable("plantId") Long plantId, @RequestBody CreateDeviceResource createDeviceResource) {
        return new ResponseEntity<>(deviceMapper.toResource(deviceService.create(deviceMapper.toModel(createDeviceResource), plantId)), HttpStatus.CREATED);
    }

    @Operation(summary = "Update device by id")
    @PutMapping("/update/{deviceId}")
    public ResponseEntity<DeviceResource> updateDevice(@PathVariable("deviceId") Long deviceId, @RequestBody CreateDeviceResource createDeviceResource) {
        return new ResponseEntity<>(deviceMapper.toResource(deviceService.update(deviceId, deviceMapper.toModel(createDeviceResource))),HttpStatus.OK);
    }

    @Operation(summary = "Patch the temperature of the device")
    @PatchMapping("/patch/temperature/{deviceId}/{temperatureValue}")
    public ResponseEntity<DeviceResource> patchDeviceTemperature(@PathVariable("deviceId") Long deviceId, @PathVariable("temperatureValue") Double temperatureValue) {
        return new ResponseEntity<>(deviceMapper.toResource(deviceService.patchDeviceTemperature(deviceId, temperatureValue)), HttpStatus.OK);
    }

    @Operation(summary = "Patch the humidity of the device")
    @PatchMapping("/patch/humidity/{deviceId}/{humidityValue}")
    public ResponseEntity<DeviceResource> patchDeviceHumidity(@PathVariable("deviceId") Long deviceId, @PathVariable("humidityValue") Double humidityValue) {
        return new ResponseEntity<>(deviceMapper.toResource(deviceService.patchDeviceHumidity(deviceId, humidityValue)), HttpStatus.OK);
    }

    @Operation(summary = "Delete device by id")
    @DeleteMapping("/delete/{deviceId}")
    public ResponseEntity<?> deleteDevice(@PathVariable("deviceId") Long deviceId) {
        return  deviceService.deleteDevice(deviceId);
    }






}
