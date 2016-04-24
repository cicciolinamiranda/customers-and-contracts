package com.g4s.javelin.util;

import java.util.List;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.PropertyChange;
import org.javers.core.diff.changetype.ReferenceChange;
import org.javers.core.diff.changetype.ValueChange;

import com.g4s.javelin.dto.core.audit.DiffDTO;
import com.google.common.collect.Lists;
import org.javers.core.diff.changetype.container.ContainerChange;
import org.javers.core.diff.changetype.container.ContainerElementChange;
import org.javers.core.diff.changetype.container.ValueAdded;
import org.javers.core.diff.changetype.container.ValueRemoved;

//CSOFF: CyclomaticComplexity
public class DiffUtil {

    protected List<DiffDTO> getOldAndNewValue(
            final Object oldValue,
            final Object newValue) {
        List<DiffDTO> response = Lists.newArrayList();
        Javers javers = JaversBuilder.javers().build();
        Diff diff = javers.compare(oldValue, newValue);
        List<PropertyChange> changes = diff.getChangesByType(PropertyChange.class);
        for (int i = 1; i < changes.size(); i++) {
            PropertyChange change = changes.get(i);

            DiffDTO diffDTO = new DiffDTO();
            diffDTO.setFieldName(change.getPropertyName());
            if (change instanceof ContainerChange) {
                for (ContainerElementChange entry : ((ContainerChange) change).getChanges()) {
                    if (entry instanceof ValueAdded) {
                        diffDTO.getChanges().add("Added #" + formatPropertyValue(entry.toString()));
                    } else if (entry instanceof ValueRemoved) {
                        diffDTO.getChanges().add("Removed #" + formatPropertyValue(entry.toString()));
                    }
                }
            } else if (change instanceof ReferenceChange) {
                ReferenceChange refChange = (ReferenceChange) change;
                String leftChange = refChange.getLeft() == null ? "" : refChange.getLeft().toString();
                String rightChange = refChange.getRight() == null ? "" : refChange.getRight().toString();

                diffDTO.getChanges().add(formatPropertyValue(leftChange)
                        + " -> " + formatPropertyValue(rightChange));
            } else if (change instanceof ValueChange) {
                ValueChange valChange = (ValueChange) change;
                change.getPropertyName()
                diffDTO.getChanges().add(valChange.getLeft() + " -> " + valChange.getRight());
            }

            response.add(diffDTO);
        }
        return response;
    }

    protected String formatPropertyValue(final String propertyValue) {
        String formatted = null;

        if (propertyValue == null) {
            formatted = "None";
        } else if (propertyValue.lastIndexOf("/") > -1) {
            formatted = propertyValue.replaceAll("^'|'$", "")
                    .substring(propertyValue.lastIndexOf("/") + 1).trim();
        } else {
            formatted = propertyValue;
        }

        return formatted;
    }

}
//CSON: CyclomaticComplexity
