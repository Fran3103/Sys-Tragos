package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.Entities.Client;
import com.SySTomateAlgo.TomateAlgo.Repositories.ClientRepository;
import com.SySTomateAlgo.TomateAlgo.Services.ClientService;
import com.SySTomateAlgo.TomateAlgo.Utils.UpdatePropertiesUtil;
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
