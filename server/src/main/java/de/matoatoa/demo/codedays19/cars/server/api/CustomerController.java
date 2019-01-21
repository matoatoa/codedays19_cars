package de.matoatoa.demo.codedays19.cars.server.api;

import de.matoatoa.demo.codedays19.cars.server.model.Customer;
import de.matoatoa.demo.codedays19.cars.server.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.unprocessableEntity;

/**
 *@author Jan Hauer
 */

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Flux<Customer> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Customer>> getById(@PathVariable String id) {
        return repository.findById(id).map(ResponseEntity::ok).defaultIfEmpty(notFound().build());
    }

    @PutMapping("/")
    public Mono<ResponseEntity<Customer>> createAndUpdate(@Valid @RequestBody Customer person) {
        return repository
                .save(person)
                .map(ResponseEntity::ok) //
                .defaultIfEmpty(unprocessableEntity().build());
    }

    @PutMapping("/{id}/lastName")
    public Mono<ResponseEntity<Customer>> updateLastName(@PathVariable String id,
                                                         @NotBlank @RequestBody String lastName) {
        return repository.findById(id) //
                .flatMap(person -> repository.save(person.withLastName(lastName))) //
                .map(ResponseEntity::ok) //
                .defaultIfEmpty(notFound().build());
    }

    @PutMapping("/{id}/address")
    public Mono<ResponseEntity<Customer>> updateAddress(@PathVariable String id,
                                                        @NotBlank @RequestBody String address) {
        return repository.findById(id) //
                .flatMap(person -> repository.save(person.withAddress(address))) //
                .map(ResponseEntity::ok) //
                .defaultIfEmpty(notFound().build());
    }
}
