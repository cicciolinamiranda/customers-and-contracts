package com.g4s.javelin.util;

import java.util.List;

import com.g4s.javelin.dto.core.audit.DiffDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.post.PostDTO;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

public class DiffUtil {

    protected List<DiffDTO> getOldAndNewValue(final CustomerLocationDTO oldValue, final CustomerLocationDTO newValue) {
        List<DiffDTO> response = Lists.newArrayList();
        //TODO: need conditions to check what fields that were changed
//        response.setFieldName();
//        response.setNewValue();
//        response.setOldValue();
        return response;
    }

    protected List<DiffDTO> getOldAndNewValue(final PostDTO oldValue, final PostDTO newValue) {
        List<DiffDTO> response = Lists.newArrayList();
        //TODO: need a condition to check if there are old and new values
//        response.setFieldName();
//        response.setNewValue();
//        response.setOldValue();
        return response;
    }
}
