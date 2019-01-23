package de.matoatoa.demo.codedays19.cars.client.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Jan Hauer
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Customer> getCustomer() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getById(id);
    }

    @PutMapping("/")
    public void createAndUpdate(@Valid @RequestBody Customer customer) {
        customerService.createOrUpdate(customer);
    }

    @PutMapping("/{id}/changeName")
    public void changeName(@PathVariable String id, @NotBlank @RequestBody String lastName) {
        customerService.changeLastName(id, lastName);
    }

    @PutMapping("/{id}/address")
    public void changeAddress(@PathVariable String id, @NotBlank @RequestBody String address) {
        customerService.changeAddress(id, address);
    }
}
