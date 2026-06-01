package br.com.orbitalwatch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "satellite_events")
public class SatelliteEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String eventType;

    @NotNull
    @Column(nullable = false)
    private Integer severity;

    private Double estimatedImpact;
    private LocalDate observationDate;
    private String status;
    private String description;
    private String satelliteSource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private SatelliteMission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private MonitoredRegion region;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AlertNotification> alerts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public SatelliteMission getMission() {
        return mission;
    }

    public void setMission(SatelliteMission v) {
        this.mission = v;
    }

    public MonitoredRegion getRegion() {
        return region;
    }

    public void setRegion(MonitoredRegion v) {
        this.region = v;
    }

    public List<AlertNotification> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertNotification> v) {
        this.alerts = v;
    }
}
