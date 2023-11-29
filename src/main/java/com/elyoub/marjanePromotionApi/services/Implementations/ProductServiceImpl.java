package com.elyoub.marjanePromotionApi.services.Implementations;

import com.elyoub.marjanePromotionApi.dtos.ManagerDTO;
import com.elyoub.marjanePromotionApi.dtos.ProoductDTO;
import com.elyoub.marjanePromotionApi.entities.Manager;
import com.elyoub.marjanePromotionApi.entities.Product;
import com.elyoub.marjanePromotionApi.helpers.EmailService;
import com.elyoub.marjanePromotionApi.observer.IObserver;
import com.elyoub.marjanePromotionApi.repositories.ProductRepository;
import com.elyoub.marjanePromotionApi.services.Interfaces.IProductService;
import com.elyoub.marjanePromotionApi.services.Interfaces.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    private ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Product> findByCIN(int id) {
        return repository.findById(id);
    }

    public ProoductDTO mapToDTO(Product product) {
        return ProoductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .build();
    }

    public Product mapToEntity(ProoductDTO prooductDTO) {
        Product product = new Product();
        product.setId(prooductDTO.getId());
        product.setName(prooductDTO.getName());
        product.setCategory(prooductDTO.getCategory());

        return product;
    }

}
