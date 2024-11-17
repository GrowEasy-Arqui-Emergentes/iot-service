package com.groweasy.iotservice.domain.persistence;

import com.groweasy.iotservice.domain.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
    Plant findByName(String name);
    Plant findByNameAndUserId(String name, String userId);
    List<Plant> findAllByUserId(String userId);
}
