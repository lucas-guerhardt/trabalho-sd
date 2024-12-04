package com.lp.central.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ClinicConfig {
    public static final String BASE_URL = "http://localhost:8081";
    public static final String GET_ALL_CONSULTATION_URL = "http://localhost:8081/consultation/";
    public static final String GET_CONSULTATION_BY_PETID_URL = "http://localhost:8081/consultation/patient/";
    public static final String GET_CONSULTATION_BY_GUARDIANID_URL = "http://localhost:8081/guardian/";
    public static final String GET_CONSULTATION_BY_GUARDIANCPF_URL = "http://localhost:8081/guardian/cpf/";
    public static final String DELETE_CONSULTATION_URL = "http://localhost:8081/delete/";
}
