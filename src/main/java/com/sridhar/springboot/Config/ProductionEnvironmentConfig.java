package com.sridhar.springboot.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile({"staging","prod"})
public class ProductionEnvironmentConfig {

    public ProductionEnvironmentConfig(){
       log.info("Staging/Production DataSource Loaded");
    }

}