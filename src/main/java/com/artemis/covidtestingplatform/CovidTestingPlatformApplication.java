package com.artemis.covidtestingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaAuditing
public class CovidTestingPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(CovidTestingPlatformApplication.class, args);
    }

}
