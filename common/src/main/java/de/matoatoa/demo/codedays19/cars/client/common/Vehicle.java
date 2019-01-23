package de.matoatoa.demo.codedays19.cars.client.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Jan Hauer (EXXETA AG)
 */

@Data
@Wither
@NoArgsConstructor
@AllArgsConstructor
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

    public boolean isSold() {
        return owner != null && dateOfPurchase != null;
    }
}