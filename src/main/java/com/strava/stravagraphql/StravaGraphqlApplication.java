package com.strava.stravagraphql;

import com.strava.stravagraphql.config.RsaConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaConfigProperties.class)
@SpringBootApplication
public class StravaGraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(StravaGraphqlApplication.class, args);
    }

}
