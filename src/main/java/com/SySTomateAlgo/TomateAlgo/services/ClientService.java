package com.SySTomateAlgo.TomateAlgo.services;

import com.SySTomateAlgo.TomateAlgo.entities.Client;


import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client save (Client client);
    List<Client> findAll();
    Optional<Client> findById(Long id);
    void delete(Long id);
    Client update (Long id, Client client);

}
