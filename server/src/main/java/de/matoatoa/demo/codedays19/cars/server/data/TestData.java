package de.matoatoa.demo.codedays19.cars.server.data;

import de.matoatoa.demo.codedays19.cars.server.model.Customer;
import de.matoatoa.demo.codedays19.cars.server.model.Vehicle;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;

/**
 * @author Jan Hauer (EXXETA AG)
 */
public class TestData {

    public static final Customer MAX = new Customer("4e2470ce", "Max", "Mustermann", "TestWorld");
    public static final Customer TIM = new Customer("569bfc40", "Tim", "Mustermann", "TestWorld");
    public static final List<Customer> CUSTOMERS = Arrays.asList(MAX, TIM);

    public final static Vehicle NEW_CAR = new Vehicle("63f1b862", null, "Tesla X", of(2018, 3, 14), null, null);
    public final static Vehicle TIMS_CAR = new Vehicle("6a99efb8", TIM, "Tesla S", of(2013, 3, 14), of(2018, 2, 5), of(2018, 3, 10));
    public static final List<Vehicle> VEHICLES = Arrays.asList(NEW_CAR, TIMS_CAR);

    private TestData() {

    }
}
