package com.SySTomateAlgo.TomateAlgo.Services;


import com.SySTomateAlgo.TomateAlgo.Entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save (Product product);
    List<Product> findAll();
    Optional <Product> findById(Long id);
    void delete(Long id);
    Product update (Long id, Product product);

}
