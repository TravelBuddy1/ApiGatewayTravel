package tn.esprit.apigatewaytravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayTravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayTravelApplication.class, args);
    }
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("feedback-route", r -> r
                        .path("/feedback/**")
                        .uri("lb://feedback"))  // Load balancer + service name

                .route("accommodation-route", r -> r
                        .path("/accommodation/**")
                        .uri("lb://accommodation-service"))

                .route("activities-route", r -> r
                        .path("/activities/**")
                        .uri("lb://activity-service"))

                .route("reservation", r -> r
                        .path("/reservations/**")
                        .uri("lb://RESERVATION-DJANGO"))

                .route("user-route", r -> r
                        .path("/users/**")
                        .uri("lb://user"))
                .build();
    }
}
