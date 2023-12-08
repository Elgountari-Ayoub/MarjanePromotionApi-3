package com.elyoub.marjanePromotionApi.Controllers;

import com.elyoub.marjanePromotionApi.dtos.ManagerDTO;
import com.elyoub.marjanePromotionApi.dtos.PromotionCenterDTO;
import com.elyoub.marjanePromotionApi.dtos.PromotionsStatistics;
import com.elyoub.marjanePromotionApi.dtos.Requests.LoginRequest;
import com.elyoub.marjanePromotionApi.entities.Implementations.PromotionCenterId;
import com.elyoub.marjanePromotionApi.entities.Manager;
import com.elyoub.marjanePromotionApi.entities.ProxyAdmin;
import com.elyoub.marjanePromotionApi.enums.PromotionStatus;
import com.elyoub.marjanePromotionApi.services.Implementations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class ManagerController {

    private PromotionCenterServiceImpl promoCenterService;
    private ProductServiceImpl productService;
    private ManagerServiceImpl service;
    private ProxyAdminServiceImpl proxyAdminService;


    @Autowired
    public ManagerController(PromotionCenterServiceImpl promoCenterService,
                             ManagerServiceImpl service, SuperAdminServiceImpl superAdminService,
                             ProxyAdminServiceImpl proxyAdminService,
                             ProductServiceImpl productService) {
        this.promoCenterService = promoCenterService;
        this.service = service;
        this.proxyAdminService = proxyAdminService;
        this.productService = productService;
    }

    @PostMapping(value = "/managers/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ManagerDTO> login(@RequestBody LoginRequest request) {
        Optional<Manager> manager = service.login(request.getEmail(), request.getPassword());
        return manager.map(managerEntity -> new ResponseEntity<>(service.mapToDTO(managerEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/managers/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ManagerDTO> save(@RequestBody ManagerDTO managerDTO) {
        Optional<ProxyAdmin> proxyAdmin = this.proxyAdminService.findByCIN("HHpx0");
        if (proxyAdmin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        managerDTO.setAdmin(proxyAdmin.get());
        Optional<Manager> manager = service.save(managerDTO);
        return manager.map(managerEntity -> new ResponseEntity<>(service.mapToDTO(managerEntity), HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

    }


    @GetMapping(value = "/managers/promotions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Page<PromotionCenterDTO>> getAllPromotionsByManager(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        Optional<Manager> managerEntity = this.service.findByCIN("HHm");
        if (managerEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

//        if(!this.service.isCurrentTimeInRange()){
//            try {
//                throw  new Exception("En tant que manager, vous ne pouvez voir les promotions que de 8 à 12 heures .");
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }

        Pageable pageable = PageRequest.of(page, size);

//        Page<PromotionCenterDTO> promotions = promoCenterService.findAllPromsByManager(managerEntity.get(), pageable)
//                .stream()
//                .map(promoCenterService::mapToDTO)
//                .collect(Collectors.toList());
        Page<PromotionCenterDTO> promotions = promoCenterService.findAllPromsByManager(managerEntity.get(), pageable)
                .map(promoCenterService::mapToDTO);

        return ResponseEntity.ok(promotions);
    }

    @PostMapping(value = "/managers/promotions/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> accept(@RequestBody PromotionCenterId promotionCenterId) {
        boolean isAccepted = promoCenterService.acceptPromotion(promotionCenterId);
        System.out.println(isAccepted);
        return ResponseEntity.ok(isAccepted);
    }

    @PostMapping(value = "/managers/promotions/refuse", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Boolean> refuse(@RequestBody PromotionCenterId promotionCenterId) {
        boolean isAccepted = promoCenterService.refusePromotion(promotionCenterId);
        System.out.println(isAccepted);
        return ResponseEntity.ok(isAccepted);
    }

    @GetMapping(value = "/managers/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PromotionsStatistics> statistics() {
        Optional<Manager> managerEntity = this.service.findByCIN("HHm");
        if (managerEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        PromotionsStatistics promotionsStatistics = new PromotionsStatistics();

        int promotionsCount = promoCenterService.countByManager(managerEntity.get());
        promotionsStatistics.setPromotionsCount(promotionsCount);

        int acceptedCount = promoCenterService.countByManagerAndStatus(managerEntity.get(), PromotionStatus.ACCEPTED);
        promotionsStatistics.setAccepted(acceptedCount);

        int refusededCount = promoCenterService.countByManagerAndStatus(managerEntity.get(), PromotionStatus.REFUSED);
        promotionsStatistics.setRefused(refusededCount);

        int pendingCount = promoCenterService.countByManagerAndStatus(managerEntity.get(), PromotionStatus.PENDING);
        promotionsStatistics.setPending(pendingCount);

        long productsCount = productService.count();
        promotionsStatistics.setProductsCount(productsCount);

        return ResponseEntity.ok(promotionsStatistics);
    }
}
