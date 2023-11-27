package com.elyoub.marjanePromotionApi.Controllers;

import com.elyoub.marjanePromotionApi.dtos.CustomerDTO;
import com.elyoub.marjanePromotionApi.dtos.PromotionCenterDTO;
import com.elyoub.marjanePromotionApi.dtos.Requests.LoginRequest;
import com.elyoub.marjanePromotionApi.entities.Customer;
import com.elyoub.marjanePromotionApi.entities.ProxyAdmin;
import com.elyoub.marjanePromotionApi.services.Implementations.CustomerServiceImpl;
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
public class CustomerController {

    private PromotionCenterServiceImpl promoCenterService;
    private CustomerServiceImpl service;
    private ProxyAdminServiceImpl proxyAdminService;



    @Autowired
    public CustomerController(PromotionCenterServiceImpl promoCenterService,
                              CustomerServiceImpl service, SuperAdminServiceImpl superAdminService,
                              ProxyAdminServiceImpl proxyAdminService) {
        this.promoCenterService = promoCenterService;
        this.service = service;
        this.proxyAdminService = proxyAdminService;
    }

    @PostMapping(value = "/customers/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CustomerDTO> login(@RequestBody LoginRequest request){
        Optional<Customer> customer = service.login(request.getEmail(), request.getPassword());
        return customer.map(customerEntity -> new ResponseEntity<>(service.mapToDTO(customerEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/customers/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO customerDTO){
        Optional<ProxyAdmin> proxyAdmin = this.proxyAdminService.findByCIN("HHpx0");
        if(proxyAdmin.isEmpty()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        customerDTO.setAdmin(proxyAdmin.get());
        Optional<Customer> customer = service.save(customerDTO);
        return customer.map(customerEntity -> new ResponseEntity<>(service.mapToDTO(customerEntity), HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

    }



//    @GetMapping(value = "/customers/promotions", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<List<PromotionCenterDTO>> getAllPromotionsByCustomer() {
//        Optional<Customer> customerEntity =  this.service.findByCIN("DQ456865");
//        if (customerEntity.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//        if(!this.service.isCurrentTimeInRange()){
//            try {
//                throw  new Exception("En tant que customer, vous ne pouvez voir les promotions que de 8 à 12 heures .");
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        List<PromotionCenterDTO> promotions = promoCenterService.findAllPromsByCustomer(customerEntity.get())
//                .stream()
//                .map(promoCenterService::mapToDTO)
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(promotions);
//    }

}
