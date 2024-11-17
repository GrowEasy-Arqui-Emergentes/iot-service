package com.groweasy.iotservice.domain.persistence;

import com.groweasy.iotservice.domain.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
    List<Project> findAllByUserId(String userId);

}
