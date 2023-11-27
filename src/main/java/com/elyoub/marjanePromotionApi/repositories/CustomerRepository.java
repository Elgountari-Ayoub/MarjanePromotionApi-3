package com.elyoub.marjanePromotionApi.repositories;

import com.elyoub.marjanePromotionApi.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByEmailAndPassword(String email, String password);
}
