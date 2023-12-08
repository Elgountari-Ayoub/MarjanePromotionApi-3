package com.elyoub.marjanePromotionApi.services.Interfaces;

import com.elyoub.marjanePromotionApi.dtos.PromotionCenterDTO;
import com.elyoub.marjanePromotionApi.entities.Implementations.PromotionCenterId;
import com.elyoub.marjanePromotionApi.entities.Manager;
import com.elyoub.marjanePromotionApi.entities.PromotionCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPromotionCenterService {

    Page<PromotionCenter> findAllPromsByManager(Manager manager, Pageable pageable);
    Optional<PromotionCenter> save(PromotionCenterDTO promotion);
    Optional<PromotionCenter>  findById(PromotionCenterId id);
    List<PromotionCenter> findAll();
    void delete(PromotionCenterId id);

    PromotionCenterDTO mapToDTO(PromotionCenter promotion);

    PromotionCenter mapToEntity(PromotionCenterDTO promotion);

    public boolean acceptPromotion(PromotionCenterId promotionCenterId);
    public boolean refusePromotion(PromotionCenterId promotionCenterId);
    public int countByManager(Manager manager) ;
}
