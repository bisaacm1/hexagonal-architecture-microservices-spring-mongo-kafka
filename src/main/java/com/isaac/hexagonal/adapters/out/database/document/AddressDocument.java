package com.isaac.hexagonal.adapters.out.database.document;

public record AddressDocument(
        String street,
        String city,
        String state
) {
}
