package com.elyoub.marjanePromotionApi.services.Interfaces;

import com.elyoub.marjanePromotionApi.entities.Manager;
import com.elyoub.marjanePromotionApi.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Optional<Product> findByCIN(int id);
}
