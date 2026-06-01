package br.com.orbitalwatch.dto.response;

public class SatelliteMissionResponseDTO {
    private Long id;
    private String missionName;
    private String responsibleAgency;
    private String originCountry;
    private Integer launchYear;
    private String missionStatus;
    private String technology;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        this.id = v;
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
}
