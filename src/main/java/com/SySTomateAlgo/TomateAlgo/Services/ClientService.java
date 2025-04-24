package com.SySTomateAlgo.TomateAlgo.Services;

import com.SySTomateAlgo.TomateAlgo.Entities.Client;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client save (Client client);
    List<Client> findAll();
    Optional<Client> findById(Long id);
    void delete(Long id);
    Client update (Long id, Client client);

}
