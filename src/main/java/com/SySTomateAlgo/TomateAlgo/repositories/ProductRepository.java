package com.SySTomateAlgo.TomateAlgo.repositories;

import com.SySTomateAlgo.TomateAlgo.entities.Product;
import com.SySTomateAlgo.TomateAlgo.entities.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

    List<Product> findByProductType(ProductType productType);

}
