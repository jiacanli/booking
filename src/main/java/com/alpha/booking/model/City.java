package com.alpha.booking.model;

public class City {
    private Long id;

    private String cityCode;

    private String cityName;

    private String referenceProvinceCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getReferenceProvinceCode() {
        return referenceProvinceCode;
    }

    public void setReferenceProvinceCode(String referenceProvinceCode) {
        this.referenceProvinceCode = referenceProvinceCode == null ? null : referenceProvinceCode.trim();
    }
}