package com.SySTomateAlgo.TomateAlgo.DTOs;

import java.util.List;

public class StationResponseDTO {
    private Long id;
    private String name;
    private List<ProductDTO> equipmients;


    public StationResponseDTO() {
    }

    public StationResponseDTO(String name, List<ProductDTO> equipmients) {
        this.name = name;
        this.equipmients = equipmients;
    }

    public StationResponseDTO(Long id, String name, List<ProductDTO> equipmients) {
        this.id = id;
        this.name = name;
        this.equipmients = equipmients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDTO> getEquipmients() {
        return equipmients;
    }

    public void setEquipmients(List<ProductDTO> equipmients) {
        this.equipmients = equipmients;
    }
}
