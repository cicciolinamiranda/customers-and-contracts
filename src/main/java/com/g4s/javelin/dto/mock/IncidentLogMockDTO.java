package com.g4s.javelin.dto.mock;

import com.g4s.javelin.dto.BaseDTO;


public class IncidentLogMockDTO extends BaseDTO {

    private String title;

    private String details;

    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(final String details) {
        this.details = details;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
