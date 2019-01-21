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
public class CustomerService {

    private final RestTemplate restTemplate;

    public CustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final ParameterizedTypeReference<List<Customer>> LIST_TYPE = new ParameterizedTypeReference<>() {
    };

    public List<Customer> getAll() {
        return restTemplate.exchange("/customers/", HttpMethod.GET, null, LIST_TYPE).getBody();
    }

    public Customer getById(final String id) {
        return restTemplate.getForEntity("/customers/" + id, Customer.class).getBody();
    }

    public void createOrUpdate(final Customer customer) {
        restTemplate.put("/customers/", customer);
    }

    public void changeLastName(final String id, final String lastName) {
        restTemplate.put("/customers/" + id + "/lastName", lastName);
    }

    public void changeAddress(final String id, final String address) {
        restTemplate.put("/customers/" + id + "/address", address);
    }

}