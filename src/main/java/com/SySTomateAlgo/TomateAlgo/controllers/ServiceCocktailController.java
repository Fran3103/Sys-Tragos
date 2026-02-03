package com.SySTomateAlgo.TomateAlgo.controllers;

import com.SySTomateAlgo.TomateAlgo.entities.ServiceCocktail;
import com.SySTomateAlgo.TomateAlgo.services.ServiceCocktailService;
import com.SySTomateAlgo.TomateAlgo.dtos.ServiceCocktailDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/{serviceId}/cocktails")
public class ServiceCocktailController {

    private final ServiceCocktailService scService;

    public ServiceCocktailController(ServiceCocktailService scService) {
        this.scService = scService;
    }

    // Agregar un cocktail con incidencia a un servicio
    @PostMapping
    public ResponseEntity<ServiceCocktail> addIncidence(
            @PathVariable Long serviceId,
            @RequestBody ServiceCocktailDTO dto
    ) {
        ServiceCocktail sc = scService.addToService(serviceId, dto.getCocktailId(), dto.getIncidence());
        return ResponseEntity.status(HttpStatus.CREATED).body(sc);
    }

    // Actualizar incidencia de un cocktail en un servicio
    @PutMapping("/{scId}")
    public ResponseEntity<ServiceCocktail> updateIncidence(
            @PathVariable Long serviceId,
            @PathVariable Long scId,
            @RequestBody ServiceCocktailDTO dto
    ) {
        ServiceCocktail sc = scService.updateIncidence(serviceId, scId, dto.getIncidence());
        return ResponseEntity.ok(sc);
    }

    // Listar todas las incidencias de un servicio
    @GetMapping
    public ResponseEntity<List<ServiceCocktail>> listIncidences(
            @PathVariable Long serviceId
    ) {
        return ResponseEntity.ok(scService.findByService(serviceId));
    }

    // Eliminar una incidencia
    @DeleteMapping("/{scId}")
    public ResponseEntity<Void> removeIncidence(
            @PathVariable Long serviceId,
            @PathVariable Long scId
    ) {
        scService.removeFromService(serviceId, scId);
        return ResponseEntity.noContent().build();
    }
}
