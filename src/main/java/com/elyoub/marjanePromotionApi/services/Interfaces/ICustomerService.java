package com.elyoub.marjanePromotionApi.services.Interfaces;

import com.elyoub.marjanePromotionApi.dtos.CustomerDTO;
import com.elyoub.marjanePromotionApi.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Optional<Customer> findByCIN(String cin);

    List<CustomerDTO> findAll();

    Optional<Customer>  save(CustomerDTO t);

    void delete(String cin);

    Optional<Customer> login(String email, String password);
}
