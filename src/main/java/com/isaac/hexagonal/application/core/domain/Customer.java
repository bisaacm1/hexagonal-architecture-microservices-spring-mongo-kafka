package com.isaac.hexagonal.application.core.domain;

public record Customer(
        String id,
        String name,
        Address address,
        String cpf,
        Boolean isValidCpf) {
}
