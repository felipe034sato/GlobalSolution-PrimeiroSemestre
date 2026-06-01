package br.com.orbitalwatch.dto.request;

import jakarta.validation.constraints.NotBlank;

public class MonitoredRegionRequestDTO {
    @NotBlank
    private String regionName;
    @NotBlank
    private String continent;
    private String areaType;
    private Double latitude;
    private Double longitude;
    private String country;
    private String climateZone;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String v) {
        this.regionName = v;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String v) {
        this.continent = v;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String v) {
        this.areaType = v;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double v) {
        this.latitude = v;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double v) {
        this.longitude = v;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String v) {
        this.country = v;
    }

    public String getClimateZone() {
        return climateZone;
    }

    public void setClimateZone(String v) {
        this.climateZone = v;
    }
}
