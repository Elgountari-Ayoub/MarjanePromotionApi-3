package com.elyoub.marjanePromotionApi.repositories;

import com.elyoub.marjanePromotionApi.entities.Implementations.PromotionCenterId;
import com.elyoub.marjanePromotionApi.entities.Manager;
import com.elyoub.marjanePromotionApi.entities.PromotionCenter;
import com.elyoub.marjanePromotionApi.enums.PromotionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionCenterRepository extends JpaRepository<PromotionCenter, PromotionCenterId> {
//    List<PromotionCenter> findAllByManager(Manager manager);
    Page<PromotionCenter> findAllByManagerOrderById(Manager manager, Pageable pageable);
    Optional<PromotionCenter> findById(PromotionCenterId id);
    int countByManager(Manager manager);
    int countByManagerAndStatus(Manager manager, PromotionStatus status);
}
