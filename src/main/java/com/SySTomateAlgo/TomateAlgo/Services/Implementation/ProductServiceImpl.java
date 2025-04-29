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
    public Product update(Long id, Product data) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado "));

        if (data.getName() != null) {
            existing.setName(data.getName());
        }
        if (data.getType() != null) {
            existing.setType(data.getType());
        }
        if (data.getAlcoholType() != null) {
            existing.setAlcoholType(data.getAlcoholType());
        }
        if (data.getCapacity() != null) {
            existing.setCapacity(data.getCapacity());
        }
        if (data.getStock() != null) {
            existing.setStock(data.getStock());
        }

        return repository.save(existing);
    }
}
