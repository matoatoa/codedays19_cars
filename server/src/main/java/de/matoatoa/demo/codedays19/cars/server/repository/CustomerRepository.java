package de.matoatoa.demo.codedays19.cars.server.repository;

import de.matoatoa.demo.codedays19.cars.server.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 *@author Jan Hauer
 */
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
