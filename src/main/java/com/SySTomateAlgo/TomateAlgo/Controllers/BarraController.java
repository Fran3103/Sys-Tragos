package com.SySTomateAlgo.TomateAlgo.Controllers;

import com.SySTomateAlgo.TomateAlgo.DTOs.BarraRequestDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.BarraResponseDTO;
import com.SySTomateAlgo.TomateAlgo.Services.BarraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barras")
public class BarraController {

    @Autowired
    public BarraService service;



    @PostMapping
    public ResponseEntity<BarraResponseDTO> create(

            @RequestBody BarraRequestDTO dto){
        BarraResponseDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<BarraResponseDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarraResponseDTO> getById(@PathVariable Long id){

        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<BarraResponseDTO>> getByEvent(@PathVariable Long eventId){
        return ResponseEntity.ok(service.findByEventId(eventId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarraResponseDTO> update(@PathVariable Long id, @RequestBody BarraRequestDTO dto){
        return ResponseEntity.ok(service.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.ok("Estacion Eliminada");

    }
}
