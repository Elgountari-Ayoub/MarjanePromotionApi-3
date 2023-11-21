package com.elyoub.marjanePromotionApi.services.Implementations;

import com.elyoub.marjanePromotionApi.entities.Product;
import com.elyoub.marjanePromotionApi.entities.Stock;
import com.elyoub.marjanePromotionApi.repositories.StockRepository;
import com.elyoub.marjanePromotionApi.services.Interfaces.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements IStockService {

    private StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock findByProduct(Product product) {
        return stockRepository.findByProduct(product);
    }
}
