package io.openliberty.guides.rest.Model;

public class Location {
    private String locId;
    private String locCity;
    private String locCountry;
    
    public Location(String locId, String locCity, String locCountry) {
        this.locId = locId;
        this.locCity = locCity;
        this.locCountry = locCountry;
    }

    public String getLocId() {
        return locId;
    }

    public void setLocId(String locId) {
        this.locId = locId;
    }

    public String getLocCity() {
        return locCity;
    }

    public void setLocCity(String locCity) {
        this.locCity = locCity;
    }

    public String getLocCountry() {
        return locCountry;
    }

    public void setLocCountry(String locCountry) {
        this.locCountry = locCountry;
    }
}
