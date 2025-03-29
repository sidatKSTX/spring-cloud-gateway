package com.consultingfirm.api_gateway_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class ApiGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayServiceApplication.class, args);
    }


}
