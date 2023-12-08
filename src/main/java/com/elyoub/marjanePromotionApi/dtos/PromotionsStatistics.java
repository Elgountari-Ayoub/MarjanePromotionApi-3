package com.elyoub.marjanePromotionApi.dtos;

import com.elyoub.marjanePromotionApi.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromotionsStatistics {
    private long productsCount;
    private int promotionsCount;
    private int accepted;
    private int refused;
    private int pending;
}
