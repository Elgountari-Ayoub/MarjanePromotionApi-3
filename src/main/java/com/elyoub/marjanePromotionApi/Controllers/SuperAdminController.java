package com.elyoub.marjanePromotionApi.Controllers;


import com.elyoub.marjanePromotionApi.dtos.Requests.LoginRequest;
import com.elyoub.marjanePromotionApi.dtos.Responses.SuperAdminResponse;
import com.elyoub.marjanePromotionApi.entities.SuperAdmin;
import com.elyoub.marjanePromotionApi.services.Implementations.SuperAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class SuperAdminController  {

    private SuperAdminServiceImpl service;

    @Autowired
    public SuperAdminController(SuperAdminServiceImpl superAdminService) {
        this.service = superAdminService;
    }


    @PostMapping(value = "/super-admin/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SuperAdminResponse> login(@RequestBody LoginRequest request){
        Optional<SuperAdmin> superAdmin = service.login(request.getEmail(), request.getPassword());
        return superAdmin.map(superAdminEntity -> new ResponseEntity<>(service.mapToDTO(superAdminEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



}
