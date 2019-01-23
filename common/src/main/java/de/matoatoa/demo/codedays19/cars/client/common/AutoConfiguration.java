package de.matoatoa.demo.codedays19.cars.client.common;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AutoConfiguration {

    /**
     * Base url for cars server
     */
    private final static String url = "http://localhost:9001";

    /**
     * user name for cars server
     */
    private final static String user = "admin";

    /**
     * password for cars server
     */
    private final static String password = "secret";

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
                .basicAuthentication(user, password) //
                .rootUri(url) //
                .build();
    }
}
