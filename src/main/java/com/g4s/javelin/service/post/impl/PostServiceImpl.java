package com.g4s.javelin.service.post.impl;

import java.util.List;
import java.util.Set;

import org.joda.time.format.DateTimeFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.g4s.javelin.data.model.masterfile.MasterfileModel;
import com.g4s.javelin.data.model.post.PostModel;
import com.g4s.javelin.data.model.post.PreferencesModel;
import com.g4s.javelin.data.repository.post.PostRepository;
import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;
import com.g4s.javelin.dto.core.post.PostDTO;
import com.g4s.javelin.dto.core.post.PreferencesDTO;
import com.g4s.javelin.service.post.PostService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.google.appengine.repackaged.com.google.api.client.util.Sets;

public class PostServiceImpl implements PostService {

    private ModelMapper modelMapper;

    @Autowired
    @Lazy
    private PostRepository postRepository;

    public PostServiceImpl() {
        modelMapper = new ModelMapper();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public PostDTO  savePostDetails(final PostDTO post) {
        PostDTO response = new PostDTO();
        PostModel model = new PostModel();
        if (post.getId() != null) {
            transformPostDTO(post, model);
        } else {
            PostModel duplicate = postRepository.findByName(post.getName());
            if (duplicate != null) {
                return null;
            } else {
                transformPostDTO(post, model);
            }
        }

        model = postRepository.save(model);
        post.setId(model.getId());
        return post;
    }

    @Override
    public List<PostDTO> getPostByCustomerLocation(final Long customerLocationId) {
        List<PostModel> results = postRepository.findByCustomerLocationId(customerLocationId);
        List<PostDTO> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(results)) {
            for (PostModel model : results) {
                list.add(transformPostModel(model));
            }
        }
        return list;
    }

    @Override
    public PostDTO getPostDetails(final Long id) {
        PostDTO dto = new PostDTO();
        PostModel result = postRepository.findOne(id);
        if (result != null) {
            dto = transformPostModel(result);
        }
        return dto;
    }

    private PostModel transformPostDTO(final PostDTO dto, final PostModel model) {
        org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setImageUrl(dto.getImageUrl());
        model.setBookOn(dto.isBookOn());
        model.setBookOff(dto.isBookOff());
        model.setCallIn(dto.isCallIn());
        model.setNotes(dto.getNotes());
        model.setNumberOfEmployees(dto.getNumberOfEmployees());
        model.setIdentificationNumber(dto.getIdentificationNumber());
        model.setIdentificationRequired(dto.isIdentificationRequired());
        if (dto.getStartDateStr() != null) {
            model.setStartDate(dtf.parseDateTime(dto.getStartDateStr()));
        }
        if (dto.getEndDateStr() != null) {
            model.setEndDate(dtf.parseDateTime(dto.getEndDateStr()));
        }
        model.setPreferences(setPreferenceModel(dto.getPreferences()));
        model.setRole(modelMapper.map(dto.getRole(), MasterfileModel.class));
        model.setSkills(transformMasterfileDTO(dto.getSkills()));
        model.setLicenses(transformMasterfileDTO(dto.getLicenses()));
        model.setUniforms(transformMasterfileDTO(dto.getUniforms()));
        model.setHealthSafetyRequirements(transformMasterfileDTO(dto.getHealthSafetyRequirements()));
        return model;
    }

    private PostDTO transformPostModel(final PostModel model) {
        org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
        PostDTO dto = new PostDTO();
        dto = modelMapper.map(model, PostDTO.class);
        dto.setPreferences(setPreferenceDTO(model.getPreferences()));
        dto.setRole(modelMapper.map(model.getRole(), MasterfileDTO.class));
        dto.setSkills(transformMasterfileModel(model.getSkills()));
        dto.setLicenses(transformMasterfileModel(model.getLicenses()));
        dto.setUniforms(transformMasterfileModel(model.getUniforms()));
        dto.setHealthSafetyRequirements(transformMasterfileModel(model.getHealthSafetyRequirements()));
        return dto;
    }

    private List<MasterfileDTO> transformMasterfileModel(final Set<MasterfileModel> models) {
        List<MasterfileDTO> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(models)) {
            for (MasterfileModel model : models) {
                list.add(modelMapper.map(model, MasterfileDTO.class));
            }
        }
        return list;
    }

    private Set<MasterfileModel> transformMasterfileDTO(final List<MasterfileDTO> dtos) {
        Set<MasterfileModel> list = Sets.newHashSet();
        if (!CollectionUtils.isEmpty(dtos)) {
            for (MasterfileDTO dto : dtos) {
                list.add(modelMapper.map(dto, MasterfileModel.class));
            }
        }
        return list;
    }

    private PreferencesModel setPreferenceModel(final PreferencesDTO dto) {
        PreferencesModel model = new PreferencesModel();
        model.setGender(modelMapper.map(dto.getGender(), MasterfileModel.class));
        model.setHeight(dto.getHeight());
        model.setLanguages(transformMasterfileDTO(dto.getLanguages()));
        model.setPhysicalConditions(transformMasterfileDTO(dto.getPhysicalConditions()));
        model.setQualifications(transformMasterfileDTO(dto.getQualifications()));
        model.setReligions(transformMasterfileDTO(dto.getReligions()));
        model.setTrainings(transformMasterfileDTO(dto.getTrainings()));

        return model;
    }

    private PreferencesDTO setPreferenceDTO(final PreferencesModel model) {
        PreferencesDTO dto = new PreferencesDTO();
        dto.setGender(modelMapper.map(model.getGender(), MasterfileDTO.class));
        dto.setHeight(model.getHeight());
        dto.setLanguages(transformMasterfileModel(model.getLanguages()));
        dto.setPhysicalConditions(transformMasterfileModel(model.getPhysicalConditions()));
        dto.setQualifications(transformMasterfileModel(model.getQualifications()));
        dto.setReligions(transformMasterfileModel(model.getReligions()));
        dto.setTrainings(transformMasterfileModel(model.getTrainings()));
        return dto;
    }
}
