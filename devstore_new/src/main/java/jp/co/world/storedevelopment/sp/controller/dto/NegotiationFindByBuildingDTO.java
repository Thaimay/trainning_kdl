package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.Building;

public class NegotiationFindByBuildingDTO {
    private Long id;
    
    private String name;
    
    public NegotiationFindByBuildingDTO(Building building) {
        setId(building.getId());
        setName(building.getName());
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

}
