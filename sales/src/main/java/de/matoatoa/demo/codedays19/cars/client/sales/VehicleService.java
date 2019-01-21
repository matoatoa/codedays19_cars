package de.matoatoa.demo.codedays19.cars.client.sales;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Jan Hauer
 */
@Service
public class VehicleService {

    private final RestTemplate restTemplate;
    private static final ParameterizedTypeReference<List<Vehicle>> LIST_TYPE = new ParameterizedTypeReference<>() {
    };

    public VehicleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Vehicle> getAll() {
        return restTemplate.exchange("/vehicles/", HttpMethod.GET, null, LIST_TYPE).getBody();
    }

    public void sale(final String vin, final Customer customer) {
        restTemplate.put("/vehicles/" + vin + "/sale", customer);
    }

    public Customer getOwner(final String vin) {
        return restTemplate.getForEntity("/vehicles/" + vin + "/owner", Customer.class).getBody();
    }
}
