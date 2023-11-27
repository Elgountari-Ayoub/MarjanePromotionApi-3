package com.elyoub.marjanePromotionApi.Controllers;

import com.elyoub.marjanePromotionApi.dtos.CashierDTO;
import com.elyoub.marjanePromotionApi.dtos.PromotionCenterDTO;
import com.elyoub.marjanePromotionApi.dtos.Requests.LoginRequest;
import com.elyoub.marjanePromotionApi.entities.Cashier;
import com.elyoub.marjanePromotionApi.entities.ProxyAdmin;
import com.elyoub.marjanePromotionApi.services.Implementations.CashierServiceImpl;
import com.elyoub.marjanePromotionApi.services.Implementations.PromotionCenterServiceImpl;
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
public class CashierController {

    private PromotionCenterServiceImpl promoCenterService;
    private CashierServiceImpl service;
    private ProxyAdminServiceImpl proxyAdminService;



    @Autowired
    public CashierController(PromotionCenterServiceImpl promoCenterService,
                             CashierServiceImpl service, SuperAdminServiceImpl superAdminService,
                             ProxyAdminServiceImpl proxyAdminService) {
        this.promoCenterService = promoCenterService;
        this.service = service;
        this.proxyAdminService = proxyAdminService;
    }

    @PostMapping(value = "/cashiers/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CashierDTO> login(@RequestBody LoginRequest request){
        Optional<Cashier> cashier = service.login(request.getEmail(), request.getPassword());
        return cashier.map(cashierEntity -> new ResponseEntity<>(service.mapToDTO(cashierEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/cashiers/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CashierDTO> save(@RequestBody CashierDTO cashierDTO){
        // get the auth proxy admin
        Optional<ProxyAdmin> proxyAdmin = this.proxyAdminService.findByCIN("HHpx0");
        if(proxyAdmin.isEmpty()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        cashierDTO.setAdmin(proxyAdmin.get());
        Optional<Cashier> cashier = service.save(cashierDTO);
        return cashier.map(cashierEntity -> new ResponseEntity<>(service.mapToDTO(cashierEntity), HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

    }
}
