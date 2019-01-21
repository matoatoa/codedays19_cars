package de.matoatoa.demo.codedays19.cars.server.repository;

import de.matoatoa.demo.codedays19.cars.server.model.Vehicle;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 *@author Jan Hauer
 */
public interface VehicleRepository extends ReactiveMongoRepository<Vehicle, String> {
}
