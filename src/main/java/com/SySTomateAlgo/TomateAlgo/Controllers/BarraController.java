package com.SySTomateAlgo.TomateAlgo.Controllers;

import com.SySTomateAlgo.TomateAlgo.Entities.Barra;
import com.SySTomateAlgo.TomateAlgo.Entities.Product;
import com.SySTomateAlgo.TomateAlgo.Services.BarraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/barra")
public class BarraController {

    @Autowired
    public BarraService service;

    @PostMapping
    public ResponseEntity<Barra> save(@RequestBody Barra barra) {
        return  ResponseEntity.ok(service.save(barra));
    }

    @GetMapping
    public ResponseEntity <List<Barra>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Barra> product =service.findById(id);
        if (product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Barra no encontrada");
        }
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Barra barra){
        Optional<Barra> product =service.findById(id);
        if (product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Barra no encontrada");
        }
        return  ResponseEntity.ok(service.update(id, barra));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Barra> product =service.findById(id);
        if (product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Barra no encontrada");
        }
        service.delete(id);
        return ResponseEntity.ok("Producto Eliminado correctamente");

    }
}
