package com.elyoub.marjanePromotionApi.services.Interfaces;

import com.elyoub.marjanePromotionApi.entities.Product;
import com.elyoub.marjanePromotionApi.entities.Stock;

public interface IStockService {

    public Stock findByProduct(Product product);
}
