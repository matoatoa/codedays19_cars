package de.matoatoa.demo.codedays19.cars.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Jan Hauer
 */

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Wither
public class Vehicle {

    @Id
    @NotNull
    private String vin;

    @Nullable
    @DBRef
    private Customer owner;

    @NotBlank
    private String vehicleModel;

    @NotNull
    private LocalDate dateOfConstruction;

    @Nullable
    private LocalDate dateOfPurchase;

    @Nullable
    private LocalDate dateOfLastService;

    @Transient
    public boolean isSold() {
        return owner != null && dateOfPurchase != null;
    }

    @Transient
    public boolean hadService() {
        return dateOfLastService != null;
    }
}