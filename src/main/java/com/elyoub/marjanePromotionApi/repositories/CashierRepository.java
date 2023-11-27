package com.elyoub.marjanePromotionApi.repositories;

import com.elyoub.marjanePromotionApi.entities.Cashier;
import com.elyoub.marjanePromotionApi.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CashierRepository extends JpaRepository<Cashier, String> {
    Optional<Cashier> findByEmailAndPassword(String email, String password);
}
