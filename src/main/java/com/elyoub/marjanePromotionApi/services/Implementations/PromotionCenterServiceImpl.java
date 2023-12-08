package com.elyoub.marjanePromotionApi.services.Implementations;

import com.elyoub.marjanePromotionApi.dtos.ManagerDTO;
import com.elyoub.marjanePromotionApi.dtos.PromotionCenterDTO;
import com.elyoub.marjanePromotionApi.entities.Implementations.PromotionCenterId;
import com.elyoub.marjanePromotionApi.entities.Manager;
import com.elyoub.marjanePromotionApi.entities.PromotionCenter;
import com.elyoub.marjanePromotionApi.enums.PromotionStatus;
import com.elyoub.marjanePromotionApi.observer.IObservable;
import com.elyoub.marjanePromotionApi.repositories.PromotionCenterRepository;
import com.elyoub.marjanePromotionApi.services.Interfaces.IPromotionCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionCenterServiceImpl implements IPromotionCenterService, IObservable {

    private PromotionCenterRepository repository;
    private ManagerServiceImpl managerService;
    private List<ManagerDTO> manager = new ArrayList<>();

    @Autowired
    public PromotionCenterServiceImpl(PromotionCenterRepository repository, ManagerServiceImpl managerService) {
        this.repository = repository;
        this.managerService = managerService;
    }

    @Override
    public Optional<PromotionCenter> findById(PromotionCenterId id) {

        return repository.findById(id);
    }

    @Override
    public List<PromotionCenter> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<PromotionCenter> findAllPromsByManager(Manager manager, Pageable pageable) {
        return repository.findAllByManagerOrderById(manager, pageable);
    }


    @Override
    public Optional<PromotionCenter> save(PromotionCenterDTO promotionDto) {
        PromotionCenter promotionCenter = repository.save(mapToEntity(promotionDto));
        return Optional.of(promotionCenter);
    }

    @Override
    public void delete(PromotionCenterId id) {

    }

    @Override
    public PromotionCenterDTO mapToDTO(PromotionCenter promotion) {
        return PromotionCenterDTO.builder().
                id(promotion.getId())
                .productPromotion(promotion.getPromotion())
                .center(promotion.getCenter())
                .manager(promotion.getManager())
                .status(promotion.getStatus())
                .performedAt(promotion.getPerformedAt())
                .build();
    }

    @Override
    public PromotionCenter mapToEntity(PromotionCenterDTO promotion) {
        PromotionCenter promotionCenter = new PromotionCenter();
        promotionCenter.setId(promotion.getId());
        promotionCenter.setPromotion(promotion.getProductPromotion());
        promotionCenter.setCenter(promotion.getCenter());
        promotionCenter.setManager(promotion.getManager());
        promotionCenter.setStatus(promotion.getStatus());
        promotionCenter.setPerformedAt(promotion.getPerformedAt());
        return promotionCenter;
    }

    @Override
    public boolean acceptPromotion(PromotionCenterId promotionCenterId) {
        try {
            // find the promotion center status
            Optional<PromotionCenter> promotionCenter = repository.findById(promotionCenterId);
            if (promotionCenter.isPresent()) {
                promotionCenter.get().setStatus(PromotionStatus.ACCEPTED);
                repository.save(promotionCenter.get());
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;

    }
    @Override
    public boolean refusePromotion(PromotionCenterId promotionCenterId) {
        try {
            // find the promotion center status
            Optional<PromotionCenter> promotionCenter = repository.findById(promotionCenterId);
            if (promotionCenter.isPresent()) {
                promotionCenter.get().setStatus(PromotionStatus.REFUSED);
                repository.save(promotionCenter.get());
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;

    }

    @Override
    public int countByManager(Manager manager) {
        return repository.countByManager(manager);
    }

    public int countByManagerAndStatus(Manager manager, PromotionStatus promotionStatus){
        return repository.countByManagerAndStatus(manager, promotionStatus);
    }

    @Override
    public void addManager(ManagerServiceImpl managerService) {

    }

    @Override
    public void removeManager(ManagerServiceImpl managerService) {

    }

    @Override
    public void notifyManager() {
        manager = managerService.findAll();
        manager.forEach(managerDTO -> {
            managerService.update(managerDTO);
        });
    }
}
