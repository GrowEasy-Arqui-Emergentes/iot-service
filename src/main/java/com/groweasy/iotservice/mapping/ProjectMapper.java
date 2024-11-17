package com.groweasy.iotservice.mapping;

import com.groweasy.iotservice.domain.model.Project;
import com.groweasy.iotservice.resource.CreateProjectResource;
import com.groweasy.iotservice.resource.ProjectResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ProjectMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;
    public ProjectResource toResource(Project model){
        return mapper.map(model, ProjectResource.class);
    }
    public Project toModel(CreateProjectResource resource){
        return mapper.map(resource, Project.class);
    }
    public Page<ProjectResource> modelListPage(List<Project> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, ProjectResource.class), pageable, modelList.size());
    }

}