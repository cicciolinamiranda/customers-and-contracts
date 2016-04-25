package com.g4s.javelin.dto.core.shiftpattern;

import com.g4s.javelin.dto.BaseDTO;

public class ShiftPatternDTO extends BaseDTO {
    private String numDays;
    private String numShifts;

    public String getNumDays() {
        return numDays;
    }

    public void setNumDays(final String numDays) {
        this.numDays = numDays;
    }

    public String getNumShifts() {
        return numShifts;
    }

    public void setNumShifts(final String numShifts) {
        this.numShifts = numShifts;
    }
}
