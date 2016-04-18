package com.g4s.javelin.data.model.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseModel;
import com.g4s.javelin.data.model.masterfile.MasterfileModel;

//CSOFF: HiddenField
@Entity
@Table(name = "LOCATIONPOST_ALLOWANCES")
public class LocationPostAllowancesModel extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private PostModel post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "allowances_id", referencedColumnName = "id")
    private MasterfileModel allowances;

    @Column(name = "IS_BILLED")
    private boolean isBilled;

    @Column(name = "AMOUNT")
    private double amount;

    public PostModel getPost() {
        return post;
    }

    public void setPost(final PostModel post) {
        this.post = post;
    }

    public MasterfileModel getAllowances() {
        return allowances;
    }

    public void setAllowances(final MasterfileModel allowances) {
        this.allowances = allowances;
    }

    public boolean isBilled() {
        return isBilled;
    }

    public void setBilled(final boolean isBilled) {
        this.isBilled = isBilled;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }
}
// CSON: HiddenField
