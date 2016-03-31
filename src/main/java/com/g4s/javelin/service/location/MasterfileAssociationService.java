package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.location.EquipmentDTO;

public interface MasterfileAssociationService {

    void saveLocationEquipment(final Long customerLocationId, List<EquipmentDTO> equipments);
    List<EquipmentDTO> getLocationEquipments(Long customerLocationId);
}
