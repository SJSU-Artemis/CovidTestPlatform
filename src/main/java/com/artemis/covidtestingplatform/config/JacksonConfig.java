package com.artemis.covidtestingplatform.config;

import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class JacksonConfig {
    @Bean
    public JtsModule jtsModule(){
        return new JtsModule();
    }
}
