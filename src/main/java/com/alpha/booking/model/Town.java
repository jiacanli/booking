package com.alpha.booking.model;

public class Town {
    private Integer id;

    private String townCode;

    private String townName;

    private String referenceCityCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode == null ? null : townCode.trim();
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName == null ? null : townName.trim();
    }

    public String getReferenceCityCode() {
        return referenceCityCode;
    }

    public void setReferenceCityCode(String referenceCityCode) {
        this.referenceCityCode = referenceCityCode == null ? null : referenceCityCode.trim();
    }
}