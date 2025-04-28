package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.Entities.Product;
import com.SySTomateAlgo.TomateAlgo.Repositories.ProductRepository;
import com.SySTomateAlgo.TomateAlgo.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;


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
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product updateData) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado "));

        existingProduct.setName(updateData.getName());
        existingProduct.setType(updateData.getType());
        existingProduct.setCapacity(updateData.getCapacity());
        existingProduct.setStock(updateData.getStock());
        existingProduct.setAlcoholType(updateData.getAlcoholType());

        return repository.save(existingProduct);
    }
}
