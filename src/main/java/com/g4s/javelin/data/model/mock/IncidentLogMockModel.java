package com.g4s.javelin.data.model.mock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseModel;

@Entity
@Table(name = "MOCK_INCIDENT_LOG")
public class IncidentLogMockModel extends BaseModel {

    @Column
    private String title;

    @Column
    private String details;

    @Column(name = "IMAGE_URL")
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
