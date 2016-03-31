package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.location.EquipmentDTO;
import com.g4s.javelin.dto.core.location.ModeTransportDTO;

public interface MasterfileAssociationService {

    void saveLocationEquipment(final Long customerLocationId, List<EquipmentDTO> equipments);
    void saveLocationModeOfTransport(final Long customerLocationId, List<ModeTransportDTO> equipments);

}
