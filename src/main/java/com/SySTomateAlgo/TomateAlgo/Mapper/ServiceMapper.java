package com.SySTomateAlgo.TomateAlgo.Mapper;

import com.SySTomateAlgo.TomateAlgo.DTOs.ServiceCocktailDTO;
import com.SySTomateAlgo.TomateAlgo.DTOs.ServiceDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.Service;
import com.SySTomateAlgo.TomateAlgo.Entities.ServiceCocktail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceMapper {

    /*public static ServiceDTO toDto(Service svc) {
        var cocktails = svc.getCocktails().stream()
                .map(ServiceMapper::toScDto)
                .toList();
        return new ServiceDTO(svc.getId(), svc.getName());
    }*/

    private static ServiceCocktailDTO toScDto(ServiceCocktail sc) {
        return new ServiceCocktailDTO(
                sc.getCocktail().getId(),
                sc.getIncidence(),
                sc.getCocktail().getName()
        );
    }
    public static Service fromDto(ServiceDTO dto) {
        Service svc = new Service();
        svc.setName(dto.getName());


        return svc;
    }
    public static ServiceDTO toDto(Service svc) {
        List<ServiceCocktailDTO> list = svc.getCocktails().stream()
                .map(sc -> new ServiceCocktailDTO(
                        sc.getCocktail().getId(),
                        sc.getIncidence(),
                        sc.getCocktail().getName()
                ))
                .collect(Collectors.toList());

        return new ServiceDTO(
                svc.getId(),
                svc.getName(),
                list
        );
    }
}
