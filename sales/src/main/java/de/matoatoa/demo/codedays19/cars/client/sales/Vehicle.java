package de.matoatoa.demo.codedays19.cars.client.sales;

import de.matoatoa.demo.codedays19.cars.client.sales.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Jan Hauer
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Wither
public class Vehicle {

    private String vin;

    private Customer owner;

    @NotBlank
    private String vehicleModel;

    @NotNull
    private LocalDate dateOfConstruction;

    @Nullable
    private LocalDate dateOfPurchase;

    @Nullable
    private LocalDate dateOfLastService;

    public boolean isSold() {
        return owner != null && dateOfPurchase != null;
    }
}