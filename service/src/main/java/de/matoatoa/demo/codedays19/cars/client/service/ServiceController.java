package de.matoatoa.demo.codedays19.cars.client.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Jan Hauer
 */
@RestController
@RequestMapping("/vehicles")
public class ServiceController {

    private final VehicleService vehicleService;

    public ServiceController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/")
    public List<Vehicle> getVehicles() {
        return vehicleService.getAll();
    }

    @GetMapping("/service")
    public List<Vehicle> getService() {
        final LocalDate deadline = LocalDate.now().minusMonths(6);
        final Predicate<Vehicle> HAD_NEVER_SERVICE = vehicle -> vehicle.getDateOfLastService() == null;
        final Predicate<Vehicle> SERVICE_TOO_LONG_AGO = vehicle -> deadline.isAfter(vehicle.getDateOfLastService());
        return getVehicles().parallelStream() //
                .filter(Vehicle::isSold) //
                .filter(HAD_NEVER_SERVICE.or(SERVICE_TOO_LONG_AGO)) //
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{vin}")
    public Customer getOwner(@PathVariable String vin) {
        return vehicleService.getOwner(vin);
    }

    @PutMapping("/service/{vin}")
    public void doService(@PathVariable String vin) {
        vehicleService.doService(vin);
    }
}
