package com.g4s.javelin.service.shiftpattern.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.data.model.shiftpattern.ShiftPatternDetailModel;
import com.g4s.javelin.data.model.shiftpattern.ShiftPatternModel;
import com.g4s.javelin.data.repository.shiftpattern.ShiftPatternDetailRepository;
import com.g4s.javelin.data.repository.shiftpattern.ShiftPatternRepository;
import com.g4s.javelin.dto.core.shiftpattern.ShiftPatternDTO;
import com.g4s.javelin.dto.core.shiftpattern.ShiftPatternDetailDTO;
import com.g4s.javelin.service.shiftpattern.ShiftPatternService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

public class ShiftPatternServiceImpl implements ShiftPatternService {

    private ModelMapper modelMapper;

    public ShiftPatternServiceImpl() {
        modelMapper = new ModelMapper();
    }

    @Autowired
    @Lazy
    private ShiftPatternRepository shiftPatternRepository;

    @Autowired
    @Lazy
    private ShiftPatternDetailRepository shiftPatternDetailRepository;

    /*
     * *(This is subject to change)*
     * Save the shift pattern, then save its detail
     */
    @Override
    public ShiftPatternDTO saveShiftPattern(final ShiftPatternDTO shiftPatternDTO, final ShiftPatternDetailDTO shiftPatternDetailDTO) {
        ShiftPatternModel model = modelMapper.map(shiftPatternDTO, ShiftPatternModel.class);
        if (shiftPatternDTO.getId() != null) {
            model.setId(shiftPatternDTO.getId());
        }
        shiftPatternRepository.save(model);

        ShiftPatternDetailModel detailModel = modelMapper.map(shiftPatternDetailDTO, ShiftPatternDetailModel.class);
        if(model.getId() != null) {
            shiftPatternDetailRepository.save(detailModel);
        }

        return transformShiftPattern(model);
    }

    @Override
    public List<ShiftPatternDTO> getAllShiftPatterns(Long id) {
        List<ShiftPatternModel> results = shiftPatternRepository.findAll();
        List<ShiftPatternDTO> list = Lists.newArrayList();
        for (ShiftPatternModel result : results) {
            list.add(transformShiftPattern(result));
        }
        return list;
    }

    /*
     * *(This is subject to change)*
     * Upon selecting a shift pattern, get the details based on the Shift Pattern ID
     */
    @Override
    public List<ShiftPatternDetailDTO> getDetailByShiftPatternId(Long shiftPatternId) {
        List<ShiftPatternDetailModel> results = shiftPatternDetailRepository.findByShiftPatternId(shiftPatternId);
        List<ShiftPatternDetailDTO> list = Lists.newArrayList();
        for (ShiftPatternDetailModel result : results) {
            list.add(transformShiftPatternDetail(result));
        }
        return list;
    }

    private ShiftPatternDTO transformShiftPattern(final ShiftPatternModel model) {
        ShiftPatternDTO dto;
        dto = modelMapper.map(model, ShiftPatternDTO.class);
        return dto;
    }

    private ShiftPatternDetailDTO transformShiftPatternDetail(final ShiftPatternDetailModel model) {
        ShiftPatternDetailDTO dto;
        dto = modelMapper.map(model, ShiftPatternDetailDTO.class);
        return dto;
    }

}
