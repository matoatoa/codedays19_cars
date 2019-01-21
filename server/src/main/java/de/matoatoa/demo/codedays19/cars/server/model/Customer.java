package de.matoatoa.demo.codedays19.cars.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *@author Jan Hauer
 */

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Wither
public class Customer {

    @Id
    @NotNull
    private String id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String address;
}
