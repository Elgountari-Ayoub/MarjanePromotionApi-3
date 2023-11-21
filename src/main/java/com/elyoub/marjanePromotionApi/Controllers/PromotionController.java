package com.elyoub.marjanePromotionApi.Controllers;

import com.elyoub.marjanePromotionApi.dtos.ProductPromotionDTO;
import com.elyoub.marjanePromotionApi.dtos.PromotionCenterDTO;
import com.elyoub.marjanePromotionApi.entities.*;
import com.elyoub.marjanePromotionApi.entities.Implementations.PromotionCenterId;
import com.elyoub.marjanePromotionApi.services.Implementations.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@NoArgsConstructor

public class PromotionController {

    private ProductPromotionServiceImpl prodPromoService;
    private PromotionCenterServiceImpl promoCenterService;
    private SuperAdminServiceImpl superAdminService;
    private ProxyAdminServiceImpl proxyAdminService;
    private ManagerServiceImpl managerService;



    @Autowired
    public PromotionController(
            ProductPromotionServiceImpl service, SuperAdminServiceImpl superAdminService, ManagerServiceImpl managerService,
            PromotionCenterServiceImpl promotionCenterService, ProxyAdminServiceImpl proxyAdminService) {

        this.prodPromoService = service;
        this.promoCenterService = promotionCenterService;
        this.superAdminService = superAdminService;
        this.proxyAdminService = proxyAdminService;
        this.managerService = managerService;
    }




    @PostMapping(value = "/promotions/products/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ProductPromotionDTO> savePromotion(@RequestBody ProductPromotionDTO promotionDTO) {
        Optional<ProxyAdmin> proxyAdmin = this.proxyAdminService.findByCIN("SQ570980");
        if (proxyAdmin.isEmpty()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Optional<ProductPromotion> promotion = prodPromoService.save(promotionDTO);
        return promotion.map(
                           productPromotion -> new ResponseEntity<>(
                                   prodPromoService.mapToDTO(productPromotion), HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

    }

    @PostMapping(value = "/promotions/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ProductPromotionDTO> updatePromotion(@PathVariable("id") Long id) {
        Optional<ProxyAdmin> proxyAdmin = this.proxyAdminService.findByCIN("SQ570980");
        if (proxyAdmin.isEmpty()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ProductPromotion promotion;
        try {
            promotion = prodPromoService.findById(id).orElseThrow(() -> new Exception("Center not found with ID "+ id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return prodPromoService.update(prodPromoService.mapToDTO(promotion)).map(
                        productPromotion -> new ResponseEntity<>(
                                prodPromoService.mapToDTO(productPromotion), HttpStatus.OK)
                ).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping(value = "/promotions/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ProductPromotionDTO> getPromotion(@PathVariable("id") Long id){
        Optional<ProductPromotion> promotion = prodPromoService.findById(id);
        return promotion.map(productPromotion -> new ResponseEntity<>(prodPromoService.mapToDTO(productPromotion), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/promotions/products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ProductPromotionDTO> getAllPromotions(){
        return prodPromoService.findAll()
                .stream()
                .map(prodPromoService::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/promotions/{centerId}/{promoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PromotionCenterDTO> getPromotionCenter(@PathVariable("centerId") Long centerId, @PathVariable("promoId") Long promoId){
        Optional<PromotionCenter> promotionCenter = promoCenterService.findById(new PromotionCenterId(centerId, promoId));
        return promotionCenter.map(promoCenter -> new ResponseEntity<>(promoCenterService.mapToDTO(promoCenter), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping(value = "/promotions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PromotionCenterDTO>> getAllPromotionsWithCenters(){

        Optional<SuperAdmin> superAdmin= this.superAdminService.findByCIN("SQ456789");
        if(superAdmin.isEmpty()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<PromotionCenterDTO> promotions = promoCenterService.findAll()
                .stream()
                .map(promoCenterService::mapToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }


    @PostMapping(value = "/promotions/apply", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PromotionCenterDTO>> ApplyToPromotions(@RequestBody List<PromotionCenterDTO>  promotions){
        Optional<Manager> manager = this.managerService.findByCIN("DQ456865");
        if(manager.isEmpty()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


        List<PromotionCenterDTO> allPromotions =  promoCenterService.findAll()
                .stream()
                .map(promoCenterService::mapToDTO)
                .collect(Collectors.toList());

        List<PromotionCenterDTO> promotionsToUpdate = promotions.stream()
                .filter(promotionCenterDTO ->
                        allPromotions.stream()
                                .noneMatch(allPromotion ->
                                        allPromotion.getId().equals(promotionCenterDTO.getId()) &&
                                                allPromotion.getStatus() == promotionCenterDTO.getStatus()
                                )
                )
                .collect(Collectors.toList());
        List<PromotionCenterDTO> promotionsList =  promotionsToUpdate.stream()
                .peek(promotionCenterDTO -> {
                    promotionCenterDTO.setPerformedAt(LocalDateTime.now());
                    promoCenterService.save(promotionCenterDTO);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(promotionsList);
    }


}
