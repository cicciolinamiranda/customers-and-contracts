package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.dto.core.masterfile.ModeTransportDTO;

public interface MasterfileAssociationService {

    void saveLocationEquipment(final Long customerLocationId, List<EquipmentDTO> equipments);
    void saveLocationModeOfTransport(final Long customerLocationId, List<ModeTransportDTO> equipments);
    List<ModeTransportDTO> getLocationModeOfTransport(Long customerLocationId);
    List<EquipmentDTO> getLocationEquipments(Long customerLocationId);
}
