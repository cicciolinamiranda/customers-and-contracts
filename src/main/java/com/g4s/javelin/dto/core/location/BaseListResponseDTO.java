package com.g4s.javelin.dto.core.location;

import java.util.List;

public class BaseListResponseDTO<T> {

    private List<T> list;
    public  List<T> getList() {
        return list;
    }
    public void setList(final  List<T> list) {
        this.list = list;
    }
}
