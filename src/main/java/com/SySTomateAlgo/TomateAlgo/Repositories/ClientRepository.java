package com.SySTomateAlgo.TomateAlgo.Repositories;

import com.SySTomateAlgo.TomateAlgo.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
