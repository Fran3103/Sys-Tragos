package com.SySTomateAlgo.TomateAlgo.Repositories;

import com.SySTomateAlgo.TomateAlgo.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

}
