package com.g4s.javelin.util;

import java.util.List;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;

import com.g4s.javelin.dto.core.audit.DiffDTO;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

public class DiffUtil {

    protected List<DiffDTO> getOldAndNewValue(
            final Object oldValue,
            final Object newValue) {
        List<DiffDTO> response = Lists.newArrayList();
        Javers javers = JaversBuilder.javers().build();
        Diff diff = javers.compare(oldValue, newValue);
        for (int i = 1; i < diff.getChangesByType(ValueChange.class).size(); i++) {
            ValueChange change = diff.getChangesByType(ValueChange.class)
                    .get(i);
            DiffDTO diffDTO = new DiffDTO();
            diffDTO.setFieldName(change.getPropertyName());
            diffDTO.setOldValue(change.getLeft().toString());
            diffDTO.setNewValue(change.getRight().toString());
            response.add(diffDTO);
        }
        return response;
    }

}
