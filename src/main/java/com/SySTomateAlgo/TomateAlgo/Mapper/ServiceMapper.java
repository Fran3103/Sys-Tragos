package com.SySTomateAlgo.TomateAlgo.Mapper;

import com.SySTomateAlgo.TomateAlgo.DTOs.ServiceCocktailDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.ServiceDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.Product;
import com.SySTomateAlgo.TomateAlgo.Entities.Service;
import com.SySTomateAlgo.TomateAlgo.Entities.ServiceCocktail;
import com.SySTomateAlgo.TomateAlgo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceMapper {

    /*public static ServiceDTO toDto(Service svc) {
        var cocktails = svc.getCocktails().stream()
                .map(ServiceMapper::toScDto)
                .toList();
        return new ServiceDTO(svc.getId(), svc.getName());
    }*/

    @Autowired
    public ProductRepository productRepository;

    private static ServiceCocktailDTO toScDto(ServiceCocktail sc) {
        return new ServiceCocktailDTO(
                sc.getCocktail().getId(),
                sc.getIncidence(),
                sc.getCocktail().getName()
        );
    }
    public  Service fromDto(ServiceDTO dto) {
        Service svc = new Service();
        svc.setId(dto.getId());
        svc.setName(dto.getName());
        if (dto.getCristaleriaIds() != null && !dto.getCristaleriaIds().isEmpty()) {
            List<Product> cristaleria = productRepository.findAllById(dto.getCristaleriaIds());
            svc.setCristaleria(cristaleria);
        }

        return svc;
    }
    public ServiceDTO toDto(Service svc) {
        List<ServiceCocktailDTO> list = svc.getCocktails().stream()
                .map(sc -> new ServiceCocktailDTO(
                        sc.getCocktail().getId(),
                        sc.getIncidence(),
                        sc.getCocktail().getName()
                ))
                .collect(Collectors.toList());


        List<Long> cristaleriaIds = svc.getCristaleria().stream()
                .map(Product::getId)
                .collect(Collectors.toList());
        return new ServiceDTO(
                svc.getId(),
                svc.getName(),
                list,
                cristaleriaIds
        );
    }
}
