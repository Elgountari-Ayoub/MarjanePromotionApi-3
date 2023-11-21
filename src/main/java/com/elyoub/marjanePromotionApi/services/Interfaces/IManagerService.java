package com.elyoub.marjanePromotionApi.services.Interfaces;

import com.elyoub.marjanePromotionApi.dtos.ManagerDTO;
import com.elyoub.marjanePromotionApi.entities.Manager;

import java.util.List;
import java.util.Optional;

public interface IManagerService {
    Optional<Manager> findByCIN(String cin);

    List<ManagerDTO> findAll();

    Optional<Manager>  save(ManagerDTO t);

    void delete(String cin);

    Optional<Manager> login(String email, String password);
}
