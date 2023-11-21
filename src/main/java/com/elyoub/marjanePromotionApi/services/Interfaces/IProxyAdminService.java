package com.elyoub.marjanePromotionApi.services.Interfaces;

import com.elyoub.marjanePromotionApi.dtos.Requests.ProxyAdminRequest;
import com.elyoub.marjanePromotionApi.dtos.Responses.ProxyAdminResponse;
import com.elyoub.marjanePromotionApi.entities.ProxyAdmin;

import java.util.List;
import java.util.Optional;

public interface IProxyAdminService {
    Optional<ProxyAdmin> findByCIN(String cin);

    List<ProxyAdminResponse> findAll();

    void delete(String cin);

    Optional<ProxyAdmin> save(ProxyAdminRequest proxyAdminRequest);

    Optional<ProxyAdmin> login(String email, String password);
}
