package com.SySTomateAlgo.TomateAlgo.services.impl;

import com.SySTomateAlgo.TomateAlgo.entities.Client;
import com.SySTomateAlgo.TomateAlgo.repositories.ClientRepository;
import com.SySTomateAlgo.TomateAlgo.services.ClientService;
import com.SySTomateAlgo.TomateAlgo.utils.UpdatePropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private  ClientRepository repository;

    @Override
    public Client save(Client client) {
        return repository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
            repository.deleteById(id);
    }

    @Override
    public Client update(Long id, Client updateData) {
        Client existClient = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado"));

        UpdatePropertiesUtil.copyNonNullProperties(updateData, existClient);


        return repository.save(existClient);
    }
}
