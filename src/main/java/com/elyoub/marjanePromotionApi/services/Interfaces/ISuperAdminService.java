package com.elyoub.marjanePromotionApi.services.Interfaces;

import com.elyoub.marjanePromotionApi.dtos.Responses.SuperAdminResponse;
import com.elyoub.marjanePromotionApi.entities.SuperAdmin;

import java.util.Optional;

public interface ISuperAdminService  {
    Optional<SuperAdmin> findByCIN(String cin);

    Optional<SuperAdmin> login(String email, String password);


    SuperAdminResponse mapToDTO(SuperAdmin superAdmin);
}
