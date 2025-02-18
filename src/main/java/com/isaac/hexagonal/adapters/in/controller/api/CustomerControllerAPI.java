package com.isaac.hexagonal.adapters.in.controller.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isaac.hexagonal.adapters.in.controller.request.CustomerRequest;
import com.isaac.hexagonal.adapters.in.controller.response.CustomerResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Customer API", description = "API for managing customers")
@RequestMapping("/api/v1/customers")
public interface CustomerControllerAPI {

    @Operation(summary = "Insert a new customer")
    @PostMapping
    ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest customerRequest);

    @Operation(summary = "Find a customer by ID")
    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> findById(@PathVariable String id);

    @Operation(summary = "Update an existing customer")
    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody CustomerRequest customerRequest);

    @Operation(summary = "Delete a customer by ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable String id);
}