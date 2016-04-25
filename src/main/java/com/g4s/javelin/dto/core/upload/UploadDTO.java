package com.g4s.javelin.dto.core.upload;

public class UploadDTO {

    private String imageUrl;
    private String imageOriginalFilename;

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageOriginalFilename() {
        return imageOriginalFilename;
    }
    public void setImageOriginalFilename(final String imageOriginalFilename) {
        this.imageOriginalFilename = imageOriginalFilename;
    }
}
