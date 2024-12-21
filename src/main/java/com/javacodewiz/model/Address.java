package com.javacodewiz.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

    @NotEmpty(message = "Area should not be empty")
    private String area;

    @NotBlank(message = "City should not be empty")
    private String city;
    @NotBlank(message = "state should not be empty")
    private String state;
    @NotBlank(message = "country should not be empty")
    private String country;
    @NotBlank(message = "pincode should not be empty")
    @Size(min = 6,max = 6,message = "pincode should be 6 digits")
    private String pincode;


}
