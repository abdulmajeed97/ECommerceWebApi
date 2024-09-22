package com.ecommerce.api.ecommerceapi.api.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationBody {

    @NotBlank(message = "This field must not be blank")
    @NotNull(message = "This field must not be blank")
    private String username;

    @Email(message = "This should be an Email")
    @NotBlank(message = "This field must not be blank")
    @NotNull(message = "This field must not be blank")
    private String email;

    @NotBlank(message = "This field must not be blank")
    @NotNull(message = "This field must not be blank")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Must contains at least one letter one number")
    @Size(min = 8, message = "Must be more than 8 ")
    private String password;

    @NotBlank(message = "This field must not be blank")
    @NotNull(message = "This field must not be blank")
    private String firstName;

    @NotBlank(message = "This field must not be blank")
    @NotNull(message = "This field must not be blank")
    private String lastName;

}
