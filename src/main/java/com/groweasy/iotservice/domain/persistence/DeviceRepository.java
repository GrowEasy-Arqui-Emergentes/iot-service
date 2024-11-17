package com.groweasy.iotservice.domain.persistence;

import com.groweasy.iotservice.domain.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findByName(String name);
    List<Device> findAllByUserId(String userId);
}
