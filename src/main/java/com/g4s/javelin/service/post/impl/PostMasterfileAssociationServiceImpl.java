package com.g4s.javelin.service.post.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;

import com.g4s.javelin.data.model.masterfile.MasterfileModel;
import com.g4s.javelin.data.model.post.LocationPostEquipmentModel;
import com.g4s.javelin.data.model.post.PostModel;
import com.g4s.javelin.data.repository.masterfile.MasterfileRepository;
import com.g4s.javelin.data.repository.post.LocationPostEquipmentRepository;
import com.g4s.javelin.data.repository.post.PostRepository;
import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.service.post.PostMasterfileAssociationService;
import com.google.common.collect.Lists;

public class PostMasterfileAssociationServiceImpl implements PostMasterfileAssociationService {

    @Autowired
    @Lazy
    private MasterfileRepository masterfileRepository;

    @Autowired
    @Lazy
    private PostRepository postRepository;

    @Autowired
    @Lazy
    private LocationPostEquipmentRepository postEquipmentRepository;

    public void savePostEquipment(final Long postId, final List<EquipmentDTO> equipments) {
        if (!CollectionUtils.isEmpty(equipments)) {
            List<LocationPostEquipmentModel> list = Lists.newArrayList();
            MasterfileModel model;
            LocationPostEquipmentModel postEquipmentModel;
            PostModel post = postRepository.findOne(postId);
            for (EquipmentDTO dto : equipments) {
                if (dto.isDeleted()) {
                    postEquipmentRepository.delete(dto.getAssociationId());
                } else {
                    model = masterfileRepository.findOne(dto.getId());
                    postEquipmentModel = new LocationPostEquipmentModel();
                    postEquipmentModel.setId(dto.getAssociationId());
                    postEquipmentModel.setPost(post);
                    postEquipmentModel.setQuantity(dto.getQuantity());
                    postEquipmentModel.setEquipment(model);
                    list.add(postEquipmentModel);
                }
            }
            if (list.size() > 0) {
                postEquipmentRepository.save(list);
            }
        }

    }
    public List<EquipmentDTO> getPostEquipments(final Long postId) {
        List<EquipmentDTO> list = Lists.newArrayList();
        EquipmentDTO equipment;
        List<LocationPostEquipmentModel> equipments = postEquipmentRepository.findByPostId(postId);
        if (!CollectionUtils.isEmpty(equipments)) {
            for (LocationPostEquipmentModel eq : equipments) {
                equipment = new EquipmentDTO();
                equipment.setAssociationId(eq.getId());
                equipment.setId(eq.getEquipment().getId());
                equipment.setName(eq.getEquipment().getName());
                equipment.setQuantity(eq.getQuantity());
                list.add(equipment);
            }
        }
        return list;

    }

}
