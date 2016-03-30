package com.g4s.javelin.dto.core.location;

public class AddressDTO {

    private String address;
    private String longitude;
    private String latitude;
    private String postCode;
    public String getAddress() {
        return address;
    }
    public void setAddress(final String address) {
        this.address = address;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(final String longitude) {
        this.longitude = longitude;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(final String latitude) {
        this.latitude = latitude;
    }
    public String getPostCode() {
        return postCode;
    }
    public void setPostCode(final String postCode) {
        this.postCode = postCode;
    }

}
