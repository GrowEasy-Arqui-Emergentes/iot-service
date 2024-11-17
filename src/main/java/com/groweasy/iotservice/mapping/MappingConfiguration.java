package com.groweasy.iotservice.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("projectMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper(){
        return new EnhancedModelMapper();
    }
    @Bean
    public ProjectMapper projectMapper() {
        return new ProjectMapper();
    }
    @Bean
    public DeviceMapper deviceMapper() {
        return new DeviceMapper();
    }

    @Bean
    public PlantMapper plantMapper() {
        return new PlantMapper();
    }

}
