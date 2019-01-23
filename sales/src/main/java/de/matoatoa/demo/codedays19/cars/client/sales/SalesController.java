package de.matoatoa.demo.codedays19.cars.client.sales;

import de.matoatoa.demo.codedays19.cars.client.common.Customer;
import de.matoatoa.demo.codedays19.cars.client.common.CustomerService;
import de.matoatoa.demo.codedays19.cars.client.common.Vehicle;
import de.matoatoa.demo.codedays19.cars.client.common.VehicleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Jan Hauer
 */
@RestController
@RequestMapping("/vehicles")
public class SalesController {

    private static final Predicate<Vehicle> NOT_SOLD = ((Predicate<Vehicle>) Vehicle::isSold).negate();
    private final VehicleService vehicleService;
    private final CustomerService customerService;

    public SalesController(VehicleService vehicleService, CustomerService customerService) {
        this.vehicleService = vehicleService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Vehicle> getVehicles() {
        return vehicleService.getAll();
    }

    @GetMapping("/available")
    public List<Vehicle> getAvailableVehicles() {
        return getVehicles().parallelStream() //
                .filter(NOT_SOLD) //
                .collect(Collectors.toList());
    }

    @PutMapping("/sale/{vin}")
    public void sale(@PathVariable String vin, @Valid @RequestBody Customer customer) {
        customerService.createOrUpdate(customer);
        vehicleService.sale(vin, customer);
    }

    @GetMapping("/owner/{vin}")
    public Customer getOwner(@PathVariable String vin) {
        return vehicleService.getOwner(vin);
    }
}
