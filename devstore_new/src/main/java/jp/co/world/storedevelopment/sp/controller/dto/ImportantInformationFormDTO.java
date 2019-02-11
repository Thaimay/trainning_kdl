package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.ImportantInformation;

public abstract class ImportantInformationFormDTO implements DTO<ImportantInformation> {

    private String content;
    private String division;
    private NegotiationDetailDTO negotiationDetailDTO;
    private Building building;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime showStartDatetime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime showEndDatetime;

    public ImportantInformation createModel() {
        return new ImportantInformation();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public NegotiationDetailDTO getNegotiationDetailDTO() {
        return negotiationDetailDTO;
    }

    public void setNegotiationDetailDTO(NegotiationDetailDTO negotiationDetailDTO) {
        this.negotiationDetailDTO = negotiationDetailDTO;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public LocalDateTime getShowStartDatetime() {
        return showStartDatetime;
    }

    public void setShowStartDatetime(LocalDateTime showStartDatetime) {
        this.showStartDatetime = showStartDatetime;
    }

    public LocalDateTime getShowEndDatetime() {
        return showEndDatetime;
    }

    public void setShowEndDatetime(LocalDateTime showEndDatetime) {
        this.showEndDatetime = showEndDatetime;
    }

}
