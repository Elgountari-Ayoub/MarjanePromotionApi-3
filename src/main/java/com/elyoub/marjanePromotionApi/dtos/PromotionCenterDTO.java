package com.elyoub.marjanePromotionApi.dtos;

import com.elyoub.marjanePromotionApi.entities.Center;
import com.elyoub.marjanePromotionApi.entities.Implementations.PromotionCenterId;
import com.elyoub.marjanePromotionApi.entities.Manager;
import com.elyoub.marjanePromotionApi.entities.ProductPromotion;
import com.elyoub.marjanePromotionApi.enums.PromotionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionCenterDTO {
    private PromotionCenterId id;
    private ProductPromotion productPromotion;
    private Center center;
    private Manager manager;
    private PromotionStatus status;
    private LocalDateTime performedAt;
}
