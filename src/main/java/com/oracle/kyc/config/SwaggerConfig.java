package com.oracle.kyc.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI kycOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("KYC Service API")
                        .description("Handles KYC submission, verification, and review")
                        .version("1.0.0"));
    }
}
