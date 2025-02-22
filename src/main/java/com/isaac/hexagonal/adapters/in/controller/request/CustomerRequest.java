package com.isaac.hexagonal.adapters.in.controller.request;

import javax.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank String name,
        @NotBlank String cpf,
        @NotBlank String zipCode) {
}
