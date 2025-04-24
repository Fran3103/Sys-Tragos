package com.SySTomateAlgo.TomateAlgo.Controllers;

import com.SySTomateAlgo.TomateAlgo.Entities.Barra;
import com.SySTomateAlgo.TomateAlgo.Services.BarraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Barra> findById(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barra> update(@PathVariable Long id, @RequestBody Barra barra){
        return  ResponseEntity.ok(service.update(id, barra));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return  ResponseEntity.ok("La barra se elimino correctamente");

    }
}
