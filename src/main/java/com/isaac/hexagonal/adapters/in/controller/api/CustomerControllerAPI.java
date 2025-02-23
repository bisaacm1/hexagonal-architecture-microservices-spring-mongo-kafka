package com.isaac.hexagonal.adapters.in.controller.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isaac.hexagonal.adapters.in.controller.request.CustomerRequest;
import com.isaac.hexagonal.adapters.in.controller.response.CustomerResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Customer API", description = "Manage customers easily with this API. You can create, find, update, and delete customers.")
@RequestMapping("/api/v1/customers")
public interface CustomerControllerAPI {

    @Operation(summary = "Create a new customer", description = "This endpoint allows you to add a new customer by providing the necessary details.")
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    @PostMapping
    ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest customerRequest);

    @Operation(summary = "Get customer details", description = "Retrieve details of a specific customer by providing their unique ID.")
    @ApiResponse(responseCode = "200", description = "Customer found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class)))
    @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> findById(@PathVariable String id);

    @Operation(summary = "Update customer information", description = "Modify the details of an existing customer by providing their ID and updated data.")
    @ApiResponse(responseCode = "204", description = "Customer updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody CustomerRequest customerRequest);

    @Operation(summary = "Delete a customer", description = "Remove a customer from the database by providing their ID.")
    @ApiResponse(responseCode = "204", description = "Customer deleted successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable String id);
}
