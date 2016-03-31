package com.g4s.javelin.api.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.location.EquipmentDTO;
import com.g4s.javelin.dto.core.location.ModeTransportDTO;
import com.g4s.javelin.dto.core.location.SkillsDTO;
import com.g4s.javelin.dto.core.location.TaskDTO;
import com.g4s.javelin.service.location.MasterFileService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

/**
 * @author Jordan Duabe
 * @since 03/28/2016
 */
@Api(
        name = ApiConstants.API_NAME,
        version = ApiConstants.API_VERSION,
        namespace = @ApiNamespace(ownerDomain = ApiConstants.API_NAMESPACE_OWNER_DOMAIN,
                ownerName = ApiConstants.API_NAMESPACE_OWNER_NAME),
        description = ApiConstants.API_DESCRIPTION)
public class MasterFileApi {

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.MASTER_FILE_SERVICE)
    private MasterFileService masterFileService;

    /**
     * Retrieve all equipments
     *
     * @return List of all available equipments
     */
    @ApiMethod(
            name = "master.file.equipment.list",
            path = "master-file/equipment",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<EquipmentDTO> getAllEquipments() {
        return masterFileService.getAllEquipments();
    }

    /**
     * Retrieve all modes of transport
     *
     * @return List of all available modes of transport
     */
    @ApiMethod(
            name = "master.file.transport.list",
            path = "master-file/transport",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ModeTransportDTO> getAllModesOfTransport() {
        return masterFileService.getAllModeTransport();
    }

    /**
     * Retrieve all skills
     *
     * @return List of all skills
     */
    @ApiMethod(
            name = "master.file.skills.list",
            path = "master-file/skills",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<SkillsDTO> getAllSkills() {
        return masterFileService.getAllSkills();
    }

    /**
     * Retrieve all tasks
     *
     * @return List of all tasks
     */
    @ApiMethod(
            name = "master.file.tasks.list",
            path = "master-file/tasks",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<TaskDTO> getAllTasks() {
        return masterFileService.getAllTasks();
    }

    @ApiMethod(
            name = "master.file.tasks.search",
            path = "master-file/tasks/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<TaskDTO> searchTasks(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchTasks(searchTerm);
    }

    @ApiMethod(
            name = "master.file.skills.search",
            path = "master-file/skills/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<SkillsDTO> searchSkills(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchSkills(searchTerm);
    }

    @ApiMethod(
            name = "master.file.transport.search",
            path = "master-file/transport/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ModeTransportDTO> searchTransport(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchModeTransport(searchTerm);
    }

    @ApiMethod(
            name = "master.file.task.search",
            path = "master-file/task/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<TaskDTO> searchTask(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchTasks(searchTerm);
    }
}
