package com.elyoub.marjanePromotionApi.dtos.Responses;

import com.elyoub.marjanePromotionApi.entities.ProductPromotion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductPromotionResponse {

    private Long id;
    private ProductPromotion promotion;
}
