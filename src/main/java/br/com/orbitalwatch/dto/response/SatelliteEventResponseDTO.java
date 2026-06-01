package br.com.orbitalwatch.dto.response;

import java.time.LocalDate;

public class SatelliteEventResponseDTO {
    private Long id;
    private String eventType;
    private Integer severity;
    private Double estimatedImpact;
    private LocalDate observationDate;
    private String status;
    private String description;
    private String satelliteSource;
    private Long missionId;
    private String missionName;
    private Long regionId;
    private String regionName;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        this.id = v;
    }

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

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String v) {
        this.missionName = v;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long v) {
        this.regionId = v;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String v) {
        this.regionName = v;
    }
}
