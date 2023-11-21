package com.elyoub.marjanePromotionApi.repositories;

import com.elyoub.marjanePromotionApi.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {
    Optional<Manager> findByEmailAndPassword(String email, String password);
}
