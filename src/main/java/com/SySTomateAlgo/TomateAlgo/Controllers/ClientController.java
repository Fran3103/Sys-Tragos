package com.SySTomateAlgo.TomateAlgo.Controllers;

import com.SySTomateAlgo.TomateAlgo.Entities.Client;
import com.SySTomateAlgo.TomateAlgo.Repositories.ClientRepository;
import com.SySTomateAlgo.TomateAlgo.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;


    @GetMapping
    public ResponseEntity<List<Client>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }


    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client){
        return ResponseEntity.ok(service.save(client));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Client> client = service.findById(id);
        if (client.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }

        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client){

        return ResponseEntity.ok(service.update(id, client));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Client> client = service.findById(id);
        if (client.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }

        service.delete(id);
        return ResponseEntity.ok("El cliente fue eliminado con exito");
    }




}
