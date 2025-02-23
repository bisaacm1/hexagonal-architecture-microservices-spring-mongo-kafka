package com.isaac.hexagonal.adapters.out.database.repository;

import com.isaac.hexagonal.adapters.out.database.document.CustomerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerDocument, String> {
}
