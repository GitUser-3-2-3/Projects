package com.brownpizza.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {

    @NotBlank(message = "Street cannot be blank")
    private String deliveryStreet;

    @NotBlank(message = "City cannot be blank")
    private String deliveryCity;

    @NotBlank(message = "State cannot be blank")
    private String deliveryState;

    @NotBlank(message = "Pin-code cannot be blank")
    @Size(min = 6, max = 6, message = "Pin-code must be 6 char in length")
    private String pinCode;
}
