package com.g4s.javelin.data.model.location;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AddressModel {

    @Column
    private String address;
    @Column
    private String longitude;
    @Column
    private String latitude;
    @Column
    private String postCode;
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getPostCode() {
        return postCode;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

}