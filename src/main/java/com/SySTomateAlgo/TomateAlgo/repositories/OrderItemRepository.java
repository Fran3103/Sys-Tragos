package com.SySTomateAlgo.TomateAlgo.repositories;

import com.SySTomateAlgo.TomateAlgo.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    void deleteByProductId(Long productId);
}