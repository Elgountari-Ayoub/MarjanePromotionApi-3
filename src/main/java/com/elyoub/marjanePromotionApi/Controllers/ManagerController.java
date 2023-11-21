package com.elyoub.marjanePromotionApi.Controllers;

import com.elyoub.marjanePromotionApi.dtos.ManagerDTO;
import com.elyoub.marjanePromotionApi.dtos.PromotionCenterDTO;
import com.elyoub.marjanePromotionApi.dtos.Requests.LoginRequest;
import com.elyoub.marjanePromotionApi.entities.Manager;
import com.elyoub.marjanePromotionApi.entities.ProxyAdmin;
import com.elyoub.marjanePromotionApi.services.Implementations.PromotionCenterServiceImpl;
import com.elyoub.marjanePromotionApi.services.Implementations.ManagerServiceImpl;
import com.elyoub.marjanePromotionApi.services.Implementations.ProxyAdminServiceImpl;
import com.elyoub.marjanePromotionApi.services.Implementations.SuperAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class ManagerController {

    private PromotionCenterServiceImpl promoCenterService;
    private ManagerServiceImpl service;
    private ProxyAdminServiceImpl proxyAdminService;



    @Autowired
    public ManagerController(PromotionCenterServiceImpl promoCenterService,
                             ManagerServiceImpl service, SuperAdminServiceImpl superAdminService,
                             ProxyAdminServiceImpl proxyAdminService) {
        this.promoCenterService = promoCenterService;
        this.service = service;
        this.proxyAdminService = proxyAdminService;
    }

    @PostMapping(value = "/managers/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ManagerDTO> login(@RequestBody LoginRequest request){
        Optional<Manager> manager = service.login(request.getEmail(), request.getPassword());
        return manager.map(managerEntity -> new ResponseEntity<>(service.mapToDTO(managerEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/managers/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ManagerDTO> save(@RequestBody ManagerDTO managerDTO){
        Optional<ProxyAdmin> proxyAdmin = this.proxyAdminService.findByCIN("SQ570980");
        if(proxyAdmin.isEmpty()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Optional<Manager> manager = service.save(managerDTO);
        return manager.map(managerEntity -> new ResponseEntity<>(service.mapToDTO(managerEntity), HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

    }



    @GetMapping(value = "/managers/promotions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PromotionCenterDTO>> getAllPromotionsByManager() {
        Optional<Manager> managerEntity =  this.service.findByCIN("DQ456865");
        if (managerEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if(!this.service.isCurrentTimeInRange()){
            try {
                throw  new Exception("En tant que manager, vous ne pouvez voir les promotions que de 8 à 12 heures .");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        List<PromotionCenterDTO> promotions = promoCenterService.findAllPromsByManager(managerEntity.get())
                .stream()
                .map(promoCenterService::mapToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(promotions);
    }

}
