package com.g4s.javelin.data.model.masterfile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseMasterfileModel;
import com.g4s.javelin.enums.MasterfileTypeEnum;

@Entity
@Table(name = "MF_LIST")
public class MasterfileModel extends BaseMasterfileModel {

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private MasterfileTypeEnum type;
    public MasterfileTypeEnum getType() {
        return type;
    }
    public void setType(final MasterfileTypeEnum type) {
        this.type = type;
    }
}
