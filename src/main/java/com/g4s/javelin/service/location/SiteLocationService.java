package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.location.SiteLocationDTO;

public interface SiteLocationService {

    void saveSiteLocation(Long customerLocationId, List<SiteLocationDTO> siteLocations);
}
