package com.javacodewiz.dto;


import com.javacodewiz.model.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;

    @NotBlank(message = "First Name should not be Empty")
    @Size(min=4,max = 20,message= "First Name should be between 4 to 20 characters")
    private String firstName;

    @NotBlank(message = "Last Name should not be Empty")
    private String lastName;

    @NotBlank(message = "Email should not be Empty")
    @Email(message = "Email should be valid Email address")
    private String email;
    @Pattern(regexp="(^$|[0-9]{10})",message = "Phone Number should be 10 digits")
    private String phoneNumber;

    @Valid
    private Address address;

}
