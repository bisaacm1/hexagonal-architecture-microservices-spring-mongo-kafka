package com.isaac.hexagonal.adapters.in.controller.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CustomerRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    @NotBlank
    private String zipCode;

}
