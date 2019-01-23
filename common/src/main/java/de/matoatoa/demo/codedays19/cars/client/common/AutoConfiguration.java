package de.matoatoa.demo.codedays19.cars.client.common;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Configuration
@ConditionalOnClass(RestTemplateBuilder.class)
@EnableConfigurationProperties(ServerProperties.class)
public class AutoConfiguration {

    private final ServerProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public CustomerService customerService(RestTemplate restTemplate) {
        return new CustomerService(restTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public VehicleService vehicleService(RestTemplate restTemplate) {
        return new VehicleService(restTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public CustomerController customerController(CustomerService customerService) {
        return new CustomerController(customerService);
    }

    @Bean
    @ConditionalOnMissingBean
    public CarsExceptionHandler carsExceptionHandler() {
        return new CarsExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .basicAuthentication(properties.getUser(), properties.getPassword()) //
                .rootUri(properties.getUrl()) //
                .build();
    }
}
