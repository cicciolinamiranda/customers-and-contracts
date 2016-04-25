package com.g4s.javelin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.shiftpattern.ShiftPatternDTO;
import com.g4s.javelin.dto.core.shiftpattern.ShiftPatternDetailDTO;
import com.g4s.javelin.service.shiftpattern.ShiftPatternService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

@Api(
        name = ApiConstants.API_NAME,
        version = ApiConstants.API_VERSION,
        namespace = @ApiNamespace(ownerDomain = ApiConstants.API_NAMESPACE_OWNER_DOMAIN,
                ownerName = ApiConstants.API_NAMESPACE_OWNER_NAME),
        description = ApiConstants.API_DESCRIPTION)
public class ShiftPatternApi {
    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.SHIFT_PATTERN_SERVICE)
    private ShiftPatternService shiftPatternService;

    /**
     * Is this list based on a certain criteria? Country? Customer? etc.
     * List of Shift Patterns
     */
    //TODO: Name and Path Entry
    @ApiMethod(
            name = "",
            path = "",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ShiftPatternDTO> listShiftPatterns(@Named("id") final Long id) {
        List<ShiftPatternDTO> response = shiftPatternService.getAllShiftPatterns(id);
        return response;
    }

    /**
     * Details for each Shift Pattern
     */
    //TODO: Name and Path Entry
    @ApiMethod(
            name = "",
            path = "",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ShiftPatternDetailDTO> listShiftPatternsDetails(@Named("shift_pattern_id") final Long id) {
        List<ShiftPatternDetailDTO> response = shiftPatternService.getDetailByShiftPatternId(id);
        return response;
    }

    /**
     * Save the shift pattern before saving its detail
     */
    //TODO: Name and Path Entry
//    @ApiMethod(
//            name = "",
//            path = "",
//            httpMethod = ApiMethod.HttpMethod.POST)
//TODO: Combine this two DTOs to a new DTO to avoid multiple Object Param
//    public ShiftPatternDTO saveShiftPattern(final ShiftPatternDTO shiftPatternDTO, final ShiftPatternDetailDTO shiftPatternDetailDTO) {
//        shiftPatternService.saveShiftPattern(shiftPatternDTO, shiftPatternDetailDTO);
//        return null;
//    }

    //TODO: Search Shift Pattern

}
