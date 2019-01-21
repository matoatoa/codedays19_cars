package de.matoatoa.demo.codedays19.cars.client.service;

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

    @NotNull
    private String vin;

    @Nullable
    private Customer owner;

    @NotBlank
    private String vehicleModel;

    @NotNull
    private LocalDate dateOfConstruction;

    @Nullable
    private LocalDate dateOfPurchase;

    @Nullable
    private LocalDate dateOfLastService;

    public boolean hadService() {
        return dateOfLastService != null;
    }

    public boolean isSold() {
        return owner != null && dateOfPurchase != null;
    }

}