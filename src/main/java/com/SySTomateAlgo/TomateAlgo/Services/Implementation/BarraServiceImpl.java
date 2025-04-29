package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.Entities.Barra;
import com.SySTomateAlgo.TomateAlgo.Repositories.BarraRepository;
import com.SySTomateAlgo.TomateAlgo.Services.BarraService;
import com.SySTomateAlgo.TomateAlgo.Utils.UpdatePropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarraServiceImpl implements BarraService {

    @Autowired
    public  BarraRepository repository;

    @Override
    public Barra save(Barra barra) {
        return repository.save(barra);
    }

    @Override
    public List<Barra> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Barra> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        System.out.println("esto llega: " + id);
        repository.deleteById(id);

    }

    @Override
    public Barra update(Long id, Barra updateData) {
        Barra existBarra = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente no encontrado"));

        UpdatePropertiesUtil.copyNonNullProperties(updateData,existBarra);

        return save(existBarra);

    }
}
