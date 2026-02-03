package com.SySTomateAlgo.TomateAlgo.services.impl;

import com.SySTomateAlgo.TomateAlgo.entities.Product;
import com.SySTomateAlgo.TomateAlgo.repositories.*;
import com.SySTomateAlgo.TomateAlgo.services.ProductService;
import com.SySTomateAlgo.TomateAlgo.utils.UpdatePropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CocktailIngredientsRepository cocktailIngredientsRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Product save(Product product) {
        return  repository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        orderItemRepository.deleteByProductId(id);
        cocktailIngredientsRepository.deleteByProductId(id);

        repository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product data) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado "));

        UpdatePropertiesUtil.copyNonNullProperties(data,existing);

        return repository.save(existing);
    }
}
