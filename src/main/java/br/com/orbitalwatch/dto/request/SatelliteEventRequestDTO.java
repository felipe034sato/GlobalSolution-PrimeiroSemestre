package br.com.orbitalwatch.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class SatelliteEventRequestDTO {
    @NotBlank
    private String eventType;
    @NotNull
    private Integer severity;
    private Double estimatedImpact;
    private LocalDate observationDate;
    private String status;
    private String description;
    private String satelliteSource;
    @NotNull
    private Long missionId;
    @NotNull
    private Long regionId;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String v) {
        this.eventType = v;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer v) {
        this.severity = v;
    }

    public Double getEstimatedImpact() {
        return estimatedImpact;
    }

    public void setEstimatedImpact(Double v) {
        this.estimatedImpact = v;
    }

    public LocalDate getObservationDate() {
        return observationDate;
    }

    public void setObservationDate(LocalDate v) {
        this.observationDate = v;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String v) {
        this.status = v;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String v) {
        this.description = v;
    }

    public String getSatelliteSource() {
        return satelliteSource;
    }

    public void setSatelliteSource(String v) {
        this.satelliteSource = v;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long v) {
        this.missionId = v;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long v) {
        this.regionId = v;
    }
}
