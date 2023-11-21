package com.elyoub.marjanePromotionApi.services.Interfaces;

import com.elyoub.marjanePromotionApi.dtos.PromotionCenterDTO;
import com.elyoub.marjanePromotionApi.entities.Implementations.PromotionCenterId;
import com.elyoub.marjanePromotionApi.entities.Manager;
import com.elyoub.marjanePromotionApi.entities.PromotionCenter;

import java.util.List;
import java.util.Optional;

public interface IPromotionCenterService {

    List<PromotionCenter> findAllPromsByManager(Manager manager);
    Optional<PromotionCenter> save(PromotionCenterDTO promotion);
    Optional<PromotionCenter>  findById(PromotionCenterId id);
    List<PromotionCenter> findAll();
    void delete(PromotionCenterId id);

    PromotionCenterDTO mapToDTO(PromotionCenter promotion);

    PromotionCenter mapToEntity(PromotionCenterDTO promotion);
}
