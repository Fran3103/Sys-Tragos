package com.SySTomateAlgo.TomateAlgo.controllers;


import com.SySTomateAlgo.TomateAlgo.entities.Event;
import com.SySTomateAlgo.TomateAlgo.services.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> findAll(){
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id){
        return eventService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("El evento no fue encontrado"));
    }


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Event event, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err ->
                    errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }

        return ResponseEntity.ok(eventService.save(event));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody Event eventData){
        return eventService.findById(id)
                .<ResponseEntity<?>>map(e -> ResponseEntity.ok(eventService.update(id, eventData)))
                .orElse(ResponseEntity.status(404).body("El Evento no fue encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return eventService.findById(id)
                .map(e-> {
                    eventService.delete(id);
                    return ResponseEntity.ok("El evento se elimino con exito");
                })
                .orElse(ResponseEntity.status(404).body("El elemento no fue encontrado"));
    }

}
