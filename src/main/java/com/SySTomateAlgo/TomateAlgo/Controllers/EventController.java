package com.SySTomateAlgo.TomateAlgo.Controllers;


import com.SySTomateAlgo.TomateAlgo.Entities.Event;
import com.SySTomateAlgo.TomateAlgo.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Event> save(@RequestBody Event event){
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
