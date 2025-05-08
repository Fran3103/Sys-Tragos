package com.SySTomateAlgo.TomateAlgo.DTOs;

import java.util.List;

public class StationRequestDTO {
    private String name;
    private List<StationEquipmentDTO> equipments;

    public StationRequestDTO() {
    }

    public StationRequestDTO(String name, List<StationEquipmentDTO> equipments) {
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
