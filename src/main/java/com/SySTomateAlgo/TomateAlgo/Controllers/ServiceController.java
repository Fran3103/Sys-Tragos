package com.SySTomateAlgo.TomateAlgo.Controllers;

import com.SySTomateAlgo.TomateAlgo.DTOs.ServiceCocktailDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.ServiceDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.Service;
import com.SySTomateAlgo.TomateAlgo.Entities.ServiceCocktail;
import com.SySTomateAlgo.TomateAlgo.Mapper.ServiceMapper;
import com.SySTomateAlgo.TomateAlgo.Services.ServiceCocktailService;
import com.SySTomateAlgo.TomateAlgo.Services.ServiceService;
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
        var svc = service.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(ServiceMapper.toDto(svc));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> update(
            @PathVariable Long id,
            @RequestBody ServiceDTO dto
    ) {
        Service entity = ServiceMapper.fromDto(dto);
        Service updated = service.update(id, entity);
        return ResponseEntity.ok(ServiceMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable  Long id){
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
    public ResponseEntity<ServiceDTO> removeCocktailFromService(
            @PathVariable Long serviceId,
            @PathVariable Long cocktailId
    ) {
        cocktailService.removeFromService(serviceId, cocktailId);
        Service updated = service.findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));
        return ResponseEntity.ok(ServiceMapper.toDto(updated));
    }

    @PostMapping("/{serviceId}/cocktails/batch")
    public ResponseEntity<ServiceDTO> addCocktailsBatch(
            @PathVariable Long serviceId,
            @RequestBody List<ServiceCocktailDTO> dtos
    ) {
        dtos.forEach(dto ->
                cocktailService.addToService(serviceId, dto.getCocktailId(), dto.getIncidence())
        );
        Service updated = service.findById(serviceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(ServiceMapper.toDto(updated));
    }
}
