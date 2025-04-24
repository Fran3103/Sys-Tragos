package com.SySTomateAlgo.TomateAlgo.Controllers;

import com.SySTomateAlgo.TomateAlgo.Entities.Service;
import com.SySTomateAlgo.TomateAlgo.Services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {


    @Autowired
    public  ServiceService service;


    @GetMapping
    public ResponseEntity<List<Service>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> findById(@PathVariable  Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> update(@PathVariable Long id, @RequestBody Service serviceData){
        return  ResponseEntity.ok(service.update(id,serviceData));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable  Long id){
        service.delete(id);
        return  ResponseEntity.ok("Servicio eliminado");
    }

    @PostMapping
    public ResponseEntity<Service> save(@RequestBody Service serviceData){
        return ResponseEntity.ok(service.save(serviceData));
    }



    @PostMapping("/{serviceId}/cockatils/{cocktailId}")
    public ResponseEntity<?> addCocktail(@PathVariable Long serviceId, @PathVariable Long cocktailId){
        try {
            Service update = service.addCocktail(serviceId, cocktailId);
            return ResponseEntity.ok(update);
        }catch (RuntimeException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


    @DeleteMapping("/{serviceId}/cocktails/{cocktailId}")
    public ResponseEntity<?> removeCocktailFromService(
            @PathVariable Long serviceId, @PathVariable Long cocktailId){
        try {
            Service updated = service.removeCocktail(serviceId, cocktailId);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
