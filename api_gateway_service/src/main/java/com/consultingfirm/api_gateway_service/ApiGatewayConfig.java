package com.consultingfirm.api_gateway_service;

import java.util.List;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/bench-profiles/**")
                        //.filters(GatewayFilterSpec::tokenRelay)
                        .uri("lb://bench-profiles-service")) //user details microservice port number
                .route(p -> p.path("/api/dailysubmissions/**")
                        .uri("lb://daily-submissions-service"))
                .route(p -> p.path("/api/interviews/**")
                        .uri("lb://interviews-service"))
                .route(p -> p.path("/api/placements/**")
                        .uri("lb://placements-service"))
                .build();
    }

   @Bean
   public CorsWebFilter corsWebFilter() {
    CorsConfiguration config = new CorsConfiguration();

    // 🔐 Specify trusted origin instead of "*"
    config.setAllowedOrigins(List.of("http://localhost:3000", "https://apigateway.probuddy.us")); // Replace with your frontend URL
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    config.setAllowedHeaders(List.of("*"));

    // ✅ Allows cookies and Authorization headers
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    return new CorsWebFilter(source);
  }

}
