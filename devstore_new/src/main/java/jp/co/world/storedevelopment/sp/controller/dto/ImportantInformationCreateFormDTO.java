package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@JsonIgnoreProperties(ignoreUnknown = true)
// @JsonDeserialize(using = LocalDateDeserializer.class)
// @JsonSerialize(using = LocalDateSerializer.class)
public class ImportantInformationCreateFormDTO extends ImportantInformationFormDTO {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static ImportantInformationCreateFormDTO toDTO(String json) {
        try {
            MAPPER.registerModule(new JavaTimeModule());
            return MAPPER.readValue(json, ImportantInformationCreateFormDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage());
        }
    }

    private List<Long> accountIds = new ArrayList<>();

    private List<Long> corporationIds = new ArrayList<>();

    private List<Long> buildingIds = new ArrayList<>();

    private List<String> corporationUnmanagedNames = new ArrayList<>();

    private List<String> buildingUnmanagedNames = new ArrayList<>();

    public String getDivision() {
        if (getCorporationIds().size() > 0) {
            return "corporation";
        } else if (getBuildingIds().size() > 0) {
            return "building";
        } else {
            return "other";
        }
    }

    public List<Long> getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(List<Long> accountIds) {
        this.accountIds = accountIds;
    }

    public List<Long> getCorporationIds() {
        return corporationIds;
    }

    public void setCorporationIds(List<Long> corporationIds) {
        this.corporationIds = corporationIds;
    }

    public List<Long> getBuildingIds() {
        return buildingIds;
    }

    public void setBuildingIds(List<Long> buildingIds) {
        this.buildingIds = buildingIds;
    }

    public List<String> getCorporationUnmanagedNames() {
        return corporationUnmanagedNames;
    }

    public void setCorporationUnmanagedNames(List<String> corporationUnmanagedNames) {
        this.corporationUnmanagedNames = corporationUnmanagedNames;
    }

    public List<String> getBuildingUnmanagedNames() {
        return buildingUnmanagedNames;
    }

    public void setBuildingUnmanagedNames(List<String> buildingUnmanagedNames) {
        this.buildingUnmanagedNames = buildingUnmanagedNames;
    }

}
