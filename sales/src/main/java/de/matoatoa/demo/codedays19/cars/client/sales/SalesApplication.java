package de.matoatoa.demo.codedays19.cars.client.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SalesApplication {

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
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .basicAuthentication(user, password) //
                .rootUri(url) //
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }

}

