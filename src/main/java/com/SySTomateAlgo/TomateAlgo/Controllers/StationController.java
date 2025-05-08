package com.SySTomateAlgo.TomateAlgo.Controllers;

import com.SySTomateAlgo.TomateAlgo.DTOs.StationRequestDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.StationResponseDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.Station;
import com.SySTomateAlgo.TomateAlgo.Services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    @Autowired
    public StationService service;



    @PostMapping
    public ResponseEntity<StationResponseDTO> create(

            @RequestBody StationRequestDTO dto){
        StationResponseDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<StationResponseDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationResponseDTO> getById(@PathVariable Long id){

        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<StationResponseDTO>> getByEvent(@PathVariable Long eventId){
        return ResponseEntity.ok(service.findByEventId(eventId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StationResponseDTO> update(@PathVariable Long id, @RequestBody StationRequestDTO dto){
        return ResponseEntity.ok(service.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.ok("Estacion Eliminada");

    }
}
