package com.consultingfirm.api_gateway_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.RouteSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.UriSpec;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
public class ApiGatewayConfigTest {

    @Mock
    private RouteLocatorBuilder routeLocatorBuilder;

    @Mock
    private RouteLocatorBuilder.Builder routesBuilder;

    @Mock
    private RouteSpec routeSpec;

    @Mock
    private UriSpec uriSpec;

    @InjectMocks
    private ApiGatewayConfig apiGatewayConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mocking the methods to return proper instances and enable chaining
        when(routeLocatorBuilder.routes()).thenReturn(routesBuilder);
        when(routesBuilder.route(any(), any(Function.class))).thenAnswer(invocation -> {
            Function<RouteSpec, UriSpec> function = invocation.getArgument(1);
            function.apply(routeSpec); // Apply the lambda
            return routesBuilder; // Allow method chaining
        });
        when(routeSpec.uri(anyString())).thenReturn(uriSpec);
        when(routesBuilder.build()).thenReturn(mock(RouteLocator.class));
    }

    @Test
    public void testGatewayRouter() {
        // Call the method under test
        RouteLocator routeLocator = apiGatewayConfig.gatewayRouter(routeLocatorBuilder);

        // Verify the RouteLocator is not null
        assertNotNull(routeLocator);

        // Verify the interactions with the mocks
        verify(routeLocatorBuilder).routes();
        verify(routesBuilder, atLeastOnce()).route(any(), any(Function.class));
        verify(routeSpec, atLeastOnce()).uri(anyString());
        verify(routesBuilder).build();
    }
}
