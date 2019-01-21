package de.matoatoa.demo.codedays19.cars.server;

import de.matoatoa.demo.codedays19.cars.server.repository.CustomerRepository;
import de.matoatoa.demo.codedays19.cars.server.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

import static de.matoatoa.demo.codedays19.cars.server.data.TestData.CUSTOMERS;
import static de.matoatoa.demo.codedays19.cars.server.data.TestData.VEHICLES;

/**
 * @author Jan Hauer
 */

@SpringBootApplication
public class ServerApplication {


    private Logger logger = LoggerFactory.getLogger(ServerApplication.class);
    private final Consumer<Throwable> printError = (throwable) -> logger.error(throwable.getMessage());

    @Bean
    public InitializingBean initCustomerRepo(CustomerRepository repository) {
        final Runnable savePersons = repository.saveAll(CUSTOMERS)::subscribe;
        return () -> repository.deleteAll().subscribe((empty) -> {
        }, printError, savePersons);
    }

    @Bean
    public InitializingBean initVehicleRepo(VehicleRepository repository) {
        final Runnable savePersons = repository.saveAll(VEHICLES)::subscribe;
        return () -> repository.deleteAll().subscribe((empty) -> {
        }, printError, savePersons);
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}

