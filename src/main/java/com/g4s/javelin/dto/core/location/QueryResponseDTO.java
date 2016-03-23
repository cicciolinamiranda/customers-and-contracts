package com.g4s.javelin.dto.core.location;

import java.util.List;

public class QueryResponseDTO<T> {

    private List<T> list;
    private String cursor;
    public  List<T> getList() {
        return list;
    }
    public void setList(final  List<T> list) {
        this.list = list;
    }
    public String getCursor() {
        return cursor;
    }
    public void setCursor(final String cursor) {
        this.cursor = cursor;
    }
}
