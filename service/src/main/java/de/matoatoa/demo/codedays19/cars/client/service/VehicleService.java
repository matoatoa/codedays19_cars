package de.matoatoa.demo.codedays19.cars.client.service;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Jan Hauer (EXXETA AG)
 */
@Service
public class VehicleService {

    private static final ParameterizedTypeReference<List<Vehicle>> LIST_TYPE = new ParameterizedTypeReference<>() {
    };
    private final RestTemplate restTemplate;

    public VehicleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Vehicle> getAll() {
        return restTemplate.exchange("/vehicles/", HttpMethod.GET, null, LIST_TYPE).getBody();
    }

    public Customer getOwner(final String vin) {
        return restTemplate.getForEntity("/vehicles/" + vin + "/owner", Customer.class).getBody();
    }

    public void doService(final String vin) {
        restTemplate.put("/vehicles/" + vin + "/lastService", LocalDate.now());
    }

}
