package de.matoatoa.demo.codedays19.cars.server.api;

import de.matoatoa.demo.codedays19.cars.server.model.Customer;
import de.matoatoa.demo.codedays19.cars.server.model.Vehicle;
import de.matoatoa.demo.codedays19.cars.server.repository.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDate;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.unprocessableEntity;

/**
 * @author Jan Hauer
 */
@RequestMapping("/vehicles")
@RestController
public class VehicleController {

    private final VehicleRepository repository;

    public VehicleController(VehicleRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Flux<Vehicle> getAll() {
        return repository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_PROCUREMENT')")
    @PutMapping("/")
    public Mono<ResponseEntity<Vehicle>> createAndUpdate(@Valid @RequestBody Vehicle vehicle) {
        return repository
                .save(vehicle)
                .map(ResponseEntity::ok) //
                .defaultIfEmpty(unprocessableEntity().build());
    }

    @GetMapping("/{vin}")
    public Mono<ResponseEntity<Vehicle>> getByVin(@PathVariable String vin) {
        return repository.findById(vin) //
                .map(ResponseEntity::ok) //
                .defaultIfEmpty(notFound().build());
    }

    @GetMapping("/{vin}/owner")
    public Mono<ResponseEntity<Customer>> getOwnerByVin(@PathVariable String vin) {
        return repository.findById(vin) //
                .filter(Vehicle::isSold) //
                .map(Vehicle::getOwner) //
                .map(ResponseEntity::ok) //
                .defaultIfEmpty(notFound().build());
    }

    @PreAuthorize("hasRole('ROLE_SALES')")
    @PutMapping("/{vin}/sale")
    public Mono<ResponseEntity<Vehicle>> sale(@PathVariable String vin, @Valid @RequestBody Customer owner) {
        return repository
                .findById(vin) //
                .flatMap(vehicle -> {
                    vehicle.setOwner(owner);
                    vehicle.setDateOfPurchase(LocalDate.now());
                    vehicle.setDateOfLastService(LocalDate.now());
                    return repository.save(vehicle);
                }) //
                .map(ResponseEntity::ok) //
                .defaultIfEmpty(notFound().build());
    }

    @GetMapping("/{vin}/lastService")
    public Mono<ResponseEntity<LocalDate>> getDateOfLastServiceByVin(@PathVariable String vin) {
        return repository.findById(vin) //
                .filter(Vehicle::hadService)
                .map(Vehicle::getDateOfLastService) //
                .map(ResponseEntity::ok) //
                .defaultIfEmpty(notFound().build());
    }

    @PreAuthorize("hasRole('ROLE_SERVICE')")
    @PutMapping("/{vin}/lastService")
    public Mono<ResponseEntity<Vehicle>> setDateOfLastService(@PathVariable String vin, @Valid @RequestBody LocalDate date) {
        return repository
                .findById(vin) //
                .flatMap(vehicle -> {
                    vehicle.setDateOfLastService(date);
                    return repository.save(vehicle);
                }) //
                .map(ResponseEntity::ok) //
                .defaultIfEmpty(notFound().build());
    }
}