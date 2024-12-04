package com.lp.clinic.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CentralConfig {
    public static final String BASE_URL = "http://localhost:8080";
    public static final String GET_ALL_PET_URL = "http://localhost:8080/pet/";
    public static final String GET_PET_URL = "http://localhost:8080/pet/";
    public static final String GET_ALL_GUARDIAN = "http://localhost:8080/guardian/";
    public static final String GET_GUARDIAN = "http://localhost:8080/guardian/";
}