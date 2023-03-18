package com.delores.medusa.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author William
 * @date 5/6/21 1:00 PM
 * @description
 */
@Component
public class Config {

    @Data
    @Component
    @ConfigurationProperties(prefix = "jwt")
    public static class Jwt {
        private String header;

        private String secret;

        private Long expiration;

        private String issuer;

        private String authenticationPath;
    }

    @Data
    @Component
    @ConfigurationProperties(prefix = "cors")
    public static class Cors {
        private List<String> allowedOrigins = new ArrayList<>();

        private List<String> allowedMethods = new ArrayList<>();

        private List<String> allowedHeaders = new ArrayList<>();
    }
}
