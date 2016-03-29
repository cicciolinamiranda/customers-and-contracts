package com.g4s.javelin.dto.core.location;

import java.util.List;

/**
 * @author Jordan Duabe
 * @since 03/29/16.
 */
public class BarredEmployeeListDTO {

    private List<BarredEmployeeDTO> barredEmployeeDTOList;

    public List<BarredEmployeeDTO> getBarredEmployeeDTOList() {
        return barredEmployeeDTOList;
    }

    public void setBarredEmployeeDTOList(List<BarredEmployeeDTO> barredEmployeeDTOList) {
        this.barredEmployeeDTOList = barredEmployeeDTOList;
    }

    @Override
    public String toString() {
        return "BarredEmployeeListDTO{" +
                "barredEmployeeDTOList=" + barredEmployeeDTOList +
                '}';
    }
}
