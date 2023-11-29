package com.elyoub.marjanePromotionApi.dtos;

import com.elyoub.marjanePromotionApi.entities.Category;
import com.elyoub.marjanePromotionApi.entities.ProxyAdmin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProoductDTO {
    private Long id;
    private Category category;
    private String name;
}
