package com.g4s.javelin.data.model.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseModel;
import com.g4s.javelin.data.model.masterfile.MasterfileModel;

@Entity
@Table(name = "LOCATIONPOST_EQUIPMENT")
public class LocationPostEquipmentModel extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private PostModel post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    private MasterfileModel equipment;

    @Column(name = "QUANTITY")
    private int quantity;

    public PostModel getPost() {
        return post;
    }

    public void setPost(final PostModel post) {
        this.post = post;
    }

    public MasterfileModel getEquipment() {
        return equipment;
    }

    public void setEquipment(final MasterfileModel equipment) {
        this.equipment = equipment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }


}
