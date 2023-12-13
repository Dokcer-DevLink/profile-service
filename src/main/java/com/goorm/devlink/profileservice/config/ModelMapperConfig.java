package com.goorm.devlink.profileservice.config;

import com.goorm.devlink.profileservice.util.ModelMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ModelMapperUtil modelMapperUtil() {
        return new ModelMapperUtil(modelMapper());
    }
}
