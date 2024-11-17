package com.groweasy.iotservice.mapping;

import com.groweasy.iotservice.domain.model.Device;
import com.groweasy.iotservice.domain.model.Project;
import com.groweasy.iotservice.resource.CreateDeviceResource;
import com.groweasy.iotservice.resource.CreateProjectResource;
import com.groweasy.iotservice.resource.DeviceResource;
import com.groweasy.iotservice.resource.ProjectResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DeviceMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;
    public DeviceResource toResource(Device model){
        return mapper.map(model, DeviceResource.class);
    }
    public Device toModel(CreateDeviceResource resource){
        return mapper.map(resource, Device.class);
    }
    public Page<DeviceResource> modelListPage(List<Project> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, DeviceResource.class), pageable, modelList.size());
    }
}
