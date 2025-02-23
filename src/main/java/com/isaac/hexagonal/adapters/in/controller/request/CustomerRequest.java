package com.isaac.hexagonal.adapters.in.controller.request;

import com.isaac.hexagonal.application.core.domain.Address;

import javax.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank String name,
        @NotBlank String cpf,
        @NotBlank String zipCode,
        Address address,
        Boolean isValidCpf
) {}
