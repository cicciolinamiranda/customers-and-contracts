package com.g4s.javelin.dto.core.shiftpattern;

import com.g4s.javelin.dto.BaseDTO;

public class ShiftPatternDetailDTO extends BaseDTO {
    private String startTime;
    private String endTime;
    private String days;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(final String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(final String endTime) {
        this.endTime = endTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(final String days) {
        this.days = days;
    }
}
