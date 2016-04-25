package com.g4s.javelin.data.model.shiftpattern;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseModel;

@Entity
@Table (name = "SHIFT_PATTERN")
public class ShiftPatternModel extends BaseModel {
    @OneToMany(mappedBy = "shiftPattern", fetch = FetchType.EAGER)
    private List<ShiftPatternDetailModel> shiftPatterns;
    @Column(name = "NUM_OF_DAYS")
    private short numDays;
    @Column(name = "NUM_OF_SHIFTS")
    private short numShifts;

    public List<ShiftPatternDetailModel> getShiftPatterns() {
        return shiftPatterns;
    }
    public void setShiftPatterns(final List<ShiftPatternDetailModel> shiftPatterns) {
        this.shiftPatterns = shiftPatterns;
    }
}
