package com.elyoub.marjanePromotionApi.dtos;

import com.elyoub.marjanePromotionApi.entities.Center;
import com.elyoub.marjanePromotionApi.entities.Product;
import com.elyoub.marjanePromotionApi.entities.ProxyAdmin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductPromotionDTO {
    private Long id;
    private Product product;
    private ProxyAdmin admin;
    private BigDecimal percentage;
    private LocalDateTime createdAt;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private List<Center> centers;
}
