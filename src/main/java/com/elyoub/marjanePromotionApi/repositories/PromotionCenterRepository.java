package com.elyoub.marjanePromotionApi.repositories;

import com.elyoub.marjanePromotionApi.entities.Implementations.PromotionCenterId;
import com.elyoub.marjanePromotionApi.entities.Manager;
import com.elyoub.marjanePromotionApi.entities.PromotionCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionCenterRepository extends JpaRepository<PromotionCenter, PromotionCenterId> {
    List<PromotionCenter> findAllByManager(Manager manager);
}
