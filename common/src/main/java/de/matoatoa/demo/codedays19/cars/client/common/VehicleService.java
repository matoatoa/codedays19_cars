package de.matoatoa.demo.codedays19.cars.client.common;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Jan Hauer
 */
public class VehicleService {

    private final RestTemplate restTemplate;


    public VehicleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Vehicle> getAll() {
        ParameterizedTypeReference<List<Vehicle>> type = new ParameterizedTypeReference<List<Vehicle>>() {
        };
        final ResponseEntity<List<Vehicle>> response = restTemplate.exchange("/vehicles/", HttpMethod.GET, null, type);
        return response.getBody();
    }

    public Customer getOwner(final String vin) {
        return restTemplate.getForEntity("/vehicles/" + vin + "/owner", Customer.class).getBody();
    }

    public void doService(final String vin) {
        restTemplate.put("/vehicles/" + vin + "/lastService", LocalDate.now());
    }


    public void sale(final String vin, final Customer customer) {
        restTemplate.put("/vehicles/" + vin + "/sale", customer);
    }
}
