package com.g4s.javelin.data.model.post;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseModel;

@Entity
@Table(name = "POST")
public class PostModel extends BaseModel {

    @Embedded
    private PreferencesModel preferenceModel;

}
