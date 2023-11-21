package com.elyoub.marjanePromotionApi.services.Implementations;

import com.elyoub.marjanePromotionApi.dtos.Responses.SuperAdminResponse;
import com.elyoub.marjanePromotionApi.entities.SuperAdmin;
import com.elyoub.marjanePromotionApi.repositories.SuperAdminRepository;
import com.elyoub.marjanePromotionApi.services.Interfaces.ISuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuperAdminServiceImpl implements ISuperAdminService {

    private  SuperAdminRepository repository;

    @Autowired
    public SuperAdminServiceImpl(SuperAdminRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<SuperAdmin> findByCIN(String cin) {
        return repository.findById(cin);
    }

    @Override
    public Optional<SuperAdmin> login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }


    @Override
    public SuperAdminResponse mapToDTO(SuperAdmin superAdmin){
        return SuperAdminResponse.builder()
                .cin(superAdmin.getCin())
                .email(superAdmin.getEmail())
                .password(superAdmin.getPassword())
                .firstName(superAdmin.getFirstName())
                .lastName(superAdmin.getLastName())
                .phone(superAdmin.getPhone())
                .build();
    }

    public SuperAdmin mapToEntity(SuperAdminResponse superAdminResponse){
        SuperAdmin superAdmin =  new SuperAdmin();
        superAdmin.setCin(superAdminResponse.getCin());
        superAdmin.setEmail(superAdminResponse.getEmail());
        superAdmin.setPassword(superAdminResponse.getPassword());
        superAdmin.setFirstName(superAdminResponse.getFirstName());
        superAdmin.setLastName(superAdminResponse.getLastName());
        superAdmin.setPhone(superAdminResponse.getPhone());

        return superAdmin;
    }



}
