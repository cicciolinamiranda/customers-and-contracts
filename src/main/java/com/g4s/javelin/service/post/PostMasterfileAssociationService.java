package com.g4s.javelin.service.post;

import java.util.List;

import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;

public interface PostMasterfileAssociationService {

    void savePostEquipment(final Long postId, List<EquipmentDTO> equipments);
    List<EquipmentDTO> getPostEquipments(Long postId);

}
