package com.g4s.javelin.service.post.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;

import com.g4s.javelin.data.model.masterfile.MasterfileModel;
import com.g4s.javelin.data.model.post.LocationPostAllowancesModel;
import com.g4s.javelin.data.model.post.LocationPostEquipmentModel;
import com.g4s.javelin.data.model.post.PostModel;
import com.g4s.javelin.data.repository.masterfile.MasterfileRepository;
import com.g4s.javelin.data.repository.post.LocationPostAllowancesRepository;
import com.g4s.javelin.data.repository.post.LocationPostEquipmentRepository;
import com.g4s.javelin.data.repository.post.PostRepository;
import com.g4s.javelin.dto.core.masterfile.AllowancesDTO;
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

    @Autowired
    @Lazy
    private LocationPostAllowancesRepository postAllowancesRepository;

    public boolean savePostEquipment(final Long postId, final List<EquipmentDTO> equipments) {
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
                return true;
            }
        }
        return false;
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

    @Override
    public boolean savePostAllowances(final Long postId,
            final List<AllowancesDTO> allowances) {
        if (!CollectionUtils.isEmpty(allowances)) {
            List<LocationPostAllowancesModel> list = Lists.newArrayList();
            MasterfileModel model;
            LocationPostAllowancesModel postAllowancesModel;
            PostModel post = postRepository.findOne(postId);
            for (AllowancesDTO dto : allowances) {
                if (dto.isDeleted()) {
                    postAllowancesRepository.delete(dto.getAssociationId());
                } else {
                    model = masterfileRepository.findOne(dto.getId());
                    postAllowancesModel = new LocationPostAllowancesModel();
                    postAllowancesModel.setId(dto.getAssociationId());
                    postAllowancesModel.setPost(post);
                    postAllowancesModel.setBilled(dto.isBilled());
                    postAllowancesModel.setAmount(dto.getAmount());
                    postAllowancesModel.setAllowances(model);
                    list.add(postAllowancesModel);
                }
            }
            if (list.size() > 0) {
                postAllowancesRepository.save(list);
                return true;
            }
        }
        return false;
    }
    @Override
    public List<AllowancesDTO> getPostAllowances(final Long postId) {
        List<AllowancesDTO> list = Lists.newArrayList();
        AllowancesDTO allowancesDTO;
        List<LocationPostAllowancesModel> allowances = postAllowancesRepository.findByPostId(postId);
        if (!CollectionUtils.isEmpty(allowances)) {
            for (LocationPostAllowancesModel loc : allowances) {
                allowancesDTO = new AllowancesDTO();
                allowancesDTO.setAssociationId(loc.getId());
                allowancesDTO.setId(loc.getAllowances().getId());
                allowancesDTO.setAmount(loc.getAmount());
                allowancesDTO.setBilled(loc.isBilled());
                allowancesDTO.setName(loc.getAllowances().getName());
                list.add(allowancesDTO);
            }
        }
        return list;
    }
}
