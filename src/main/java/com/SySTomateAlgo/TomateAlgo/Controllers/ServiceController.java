package com.SySTomateAlgo.TomateAlgo.Controllers;

import com.SySTomateAlgo.TomateAlgo.DTOs.ServiceCocktailDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.ServiceDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.Service;
import com.SySTomateAlgo.TomateAlgo.Entities.ServiceCocktail;
import com.SySTomateAlgo.TomateAlgo.Mapper.ServiceMapper;
import com.SySTomateAlgo.TomateAlgo.Services.ServiceCocktailService;
import com.SySTomateAlgo.TomateAlgo.Services.ServiceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/services")
public class ServiceController {


    @Autowired
    public  ServiceService service;

    @Autowired
    public ServiceCocktailService cocktailService;


    @GetMapping
    public ResponseEntity<List<ServiceDTO>> findAll(){
        var list = service.findAll().stream().map(ServiceMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> findById(@PathVariable  Long id){
        Service svc = service.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio no encontrado"));

        return ResponseEntity.ok(ServiceMapper.toDto(svc));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody ServiceDTO dto
    ) {
        if (service.findById(id).isEmpty() ) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Servicio no encontrado");
        }
        Service entity = ServiceMapper.fromDto(dto);
        Service updated = service.update(id, entity);
        return ResponseEntity.ok(ServiceMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable  Long id){
        if (service.findById(id).isEmpty() ) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Servicio no encontrado");
        }
        service.delete(id);
        return  ResponseEntity.ok("Servicio eliminado");
    }

    @PostMapping
    public ResponseEntity<ServiceDTO> save(@RequestBody ServiceDTO serviceData){

        Service saved = service.saveNew(serviceData.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(ServiceMapper.toDto(saved));
     }



    @PostMapping("/{serviceId}/cockatils")
    public ResponseEntity<ServiceDTO> addCocktail(
            @PathVariable Long serviceId,
            @RequestBody ServiceCocktailDTO dto
    ) {
        cocktailService.addToService(serviceId, dto.getCocktailId(), dto.getIncidence());
        Service updated = service.findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));
        return ResponseEntity.ok(ServiceMapper.toDto(updated));
    }


    @DeleteMapping("/{serviceId}/cocktails/{Id}")
    public ResponseEntity<?> removeCocktailFromService(
            @PathVariable Long serviceId,
            @PathVariable Long cocktailId
    ) {
        if (service.findById(serviceId).isEmpty() ) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Servicio no encontrado");
        }
        cocktailService.removeFromService(serviceId, cocktailId);
        Service updated = service.findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));
        return ResponseEntity.ok(ServiceMapper.toDto(updated));
    }

    @PostMapping("/{serviceId}/cocktails/batch")
    public ResponseEntity<?> addCocktailsBatch(
            @PathVariable Long serviceId,
            @RequestBody List<ServiceCocktailDTO> dtos
    ) {
        if (service.findById(serviceId).isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Servicio no encontrado");
        }
        for(ServiceCocktailDTO dto : dtos) {

                try {
                    cocktailService.addToService(serviceId,dto.getCocktailId(),dto.getIncidence());
                }catch (EntityNotFoundException ex){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al añadir cocktail "+ dto.getCocktailId() + ": " + ex.getMessage());
                }catch (RuntimeException ex) {

                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body("Error en los datos del cocktail " + dto.getCocktailId() + ": " + ex.getMessage());
                }

        }
        Service update = service.findById(serviceId).get();
        return ResponseEntity.ok(ServiceMapper.toDto(update));
    }
}
