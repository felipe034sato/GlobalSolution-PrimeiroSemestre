package br.com.orbitalwatch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "satellite_missions")
public class SatelliteMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String missionName;

    @NotBlank
    @Column(nullable = false)
    private String responsibleAgency;

    private String originCountry;
    private Integer launchYear;
    private String missionStatus;
    private String technology;
    private String description;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SatelliteEvent> events;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String v) {
        this.missionName = v;
    }

    public String getResponsibleAgency() {
        return responsibleAgency;
    }

    public void setResponsibleAgency(String v) {
        this.responsibleAgency = v;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String v) {
        this.originCountry = v;
    }

    public Integer getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(Integer v) {
        this.launchYear = v;
    }

    public String getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(String v) {
        this.missionStatus = v;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String v) {
        this.technology = v;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String v) {
        this.description = v;
    }

    public List<SatelliteEvent> getEvents() {
        return events;
    }

    public void setEvents(List<SatelliteEvent> v) {
        this.events = v;
    }
}
