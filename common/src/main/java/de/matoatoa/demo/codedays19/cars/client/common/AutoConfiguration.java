package de.matoatoa.demo.codedays19.cars.client.common;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Configuration
@EnableConfigurationProperties(ServerProperties.class)
public class AutoConfiguration {

    private final ServerProperties properties;

    @Bean
    public CustomerService customerService(RestTemplate restTemplate) {
        return new CustomerService(restTemplate);
    }

    @Bean
    public VehicleService vehicleService(RestTemplate restTemplate) {
        return new VehicleService(restTemplate);
    }

    @Bean
    public CustomerController customerController(CustomerService customerService) {
        return new CustomerController(customerService);
    }

    @Bean
    public CarsExceptionHandler carsExceptionHandler() {
        return new CarsExceptionHandler();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .basicAuthentication(properties.getUser(), properties.getPassword()) //
                .rootUri(properties.getUrl()) //
                .build();
    }
}
