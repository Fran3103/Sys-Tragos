package com.SySTomateAlgo.TomateAlgo.DTOs;

import java.util.List;

public class BarraRequestDTO {
    private String name;
    private List<StationEquipmentDTO> equipments;

    public BarraRequestDTO() {
    }

    public BarraRequestDTO(String name, List<StationEquipmentDTO> equipments) {
        this.name = name;
        this.equipments = equipments;
    }


    public List<StationEquipmentDTO> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<StationEquipmentDTO> equipments) {
        this.equipments = equipments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
