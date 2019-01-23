package de.matoatoa.demo.codedays19.cars.client.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Jan Hauer (EXXETA AG)
 */

@Data
@Wither
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @NotNull
    private String id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String address;
}
