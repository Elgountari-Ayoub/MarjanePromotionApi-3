package com.elyoub.marjanePromotionApi.services.Interfaces;

import com.elyoub.marjanePromotionApi.dtos.CashierDTO;
import com.elyoub.marjanePromotionApi.entities.Cashier;

import java.util.List;
import java.util.Optional;

public interface ICashierService {
    Optional<Cashier> findByCIN(String cin);

    List<CashierDTO> findAll();

    Optional<Cashier>  save(CashierDTO t);

    void delete(String cin);

    Optional<Cashier> login(String email, String password);
}
