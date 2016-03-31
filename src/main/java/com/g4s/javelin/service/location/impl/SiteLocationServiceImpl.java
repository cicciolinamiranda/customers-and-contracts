package com.g4s.javelin.service.location.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;

import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.data.model.location.SiteLocationModel;
import com.g4s.javelin.data.repository.location.CustomerLocationRepository;
import com.g4s.javelin.data.repository.location.SiteLocationRepository;
import com.g4s.javelin.dto.core.location.SiteLocationDTO;
import com.g4s.javelin.service.location.SiteLocationService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

public class SiteLocationServiceImpl implements SiteLocationService {

    @Autowired
    @Lazy
    private SiteLocationRepository siteLocationRepository;

    @Autowired
    @Lazy
    private CustomerLocationRepository customerLocationRepository;

    private ModelMapper modelMapper;

    public SiteLocationServiceImpl() {
        modelMapper = new ModelMapper();
    }

    @Override
    public void saveSiteLocation(final Long customerLocationId, final List<SiteLocationDTO> siteLocations) {
        List<SiteLocationModel> list = Lists.newArrayList();
        CustomerLocationModel cl = customerLocationRepository.findOne(customerLocationId);
        if (!CollectionUtils.isEmpty(siteLocations)) {
            for (SiteLocationDTO dto : siteLocations) {
                SiteLocationModel sl = new SiteLocationModel();
                sl = modelMapper.map(dto, SiteLocationModel.class);
                sl.setCustomerLocation(cl);
                list.add(sl);
            }
            siteLocationRepository.save(list);
        }
    }

}
