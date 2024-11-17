package com.groweasy.iotservice.mapping;

import com.groweasy.iotservice.domain.model.Device;
import com.groweasy.iotservice.domain.model.Plant;
import com.groweasy.iotservice.domain.model.Project;
import com.groweasy.iotservice.resource.CreateDeviceResource;
import com.groweasy.iotservice.resource.CreatePlantResource;
import com.groweasy.iotservice.resource.DeviceResource;
import com.groweasy.iotservice.resource.PlantResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class PlantMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public PlantResource toResource(Plant model){
        return mapper.map(model, PlantResource.class);
    }
    public Plant toModel(CreatePlantResource resource){
        return mapper.map(resource, Plant.class);
    }
    public Page<PlantResource> modelListPage(List<Project> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, PlantResource.class), pageable, modelList.size());
    }
}
