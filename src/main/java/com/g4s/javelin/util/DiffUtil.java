package com.g4s.javelin.util;

import com.g4s.javelin.dto.core.audit.DiffDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.post.PostDTO;

public class DiffUtil {

    protected DiffDTO getOldAndNewValue(final CustomerLocationDTO oldValue, final CustomerLocationDTO newValue) {
        DiffDTO response = new DiffDTO();
        //TODO: need conditions to check what fields that were changed
//        response.setFieldName();
//        response.setNewValue();
//        response.setOldValue();
        return response;
    }

    protected DiffDTO getOldAndNewValue(final PostDTO oldValue, final PostDTO newValue) {
        DiffDTO response = new DiffDTO();
        //TODO: need a condition to check if there are old and new values
//        response.setFieldName();
//        response.setNewValue();
//        response.setOldValue();
        return response;
    }
}
