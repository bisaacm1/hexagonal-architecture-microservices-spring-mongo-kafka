package com.isaac.hexagonal.adapters.out.database.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public record CustomerDocument(
        @Id String id,
        String name,
        AddressDocument address,
        String cpf,
        Boolean isValidCpf
) {
}
