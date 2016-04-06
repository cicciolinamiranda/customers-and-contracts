package com.g4s.javelin.api.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;
import com.g4s.javelin.dto.core.masterfile.TaskDTO;
import com.g4s.javelin.enums.MasterfileTypeEnum;
import com.g4s.javelin.service.location.MasterfileService;
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
    private MasterfileService masterFileService;

    /**
     * Retrieve all equipments
     *
     * @return List of all available equipments
     */
    @ApiMethod(
            name = "master.file.equipment.list",
            path = "master-file/equipment",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllEquipments() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.LOCATION_EQUIPMENT);
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
    public List<MasterfileDTO> getAllModesOfTransport() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.MODE_TRANSPORT);
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
    public List<MasterfileDTO> getAllSkills() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.LOCATION_SKILLS);
    }

    /**
     * Retrieve all proof of duty
     *
     * @return List of all proof of duty
     */
    @ApiMethod(
            name = "master.file.proofofduty.list",
            path = "master-file/proof-of-duty",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllProofOfDuty() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.PROOF_OF_DUTY);
    }

    /**
     * Retrieve all method of recording
     *
     * @return List of all method of recording
     */
    @ApiMethod(
            name = "master.file.methodofrecording.list",
            path = "master-file/method-of-recording",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllMethodOfRecording() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.METHOD_OF_RECORDING);
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
    public List<MasterfileDTO> searchSkills(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchMasterfileByTypeAndName(MasterfileTypeEnum.LOCATION_SKILLS, searchTerm);
    }

    @ApiMethod(
            name = "master.file.transport.search",
            path = "master-file/transport/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> searchTransport(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchMasterfileByTypeAndName(MasterfileTypeEnum.MODE_TRANSPORT, searchTerm);
    }

    @ApiMethod(
            name = "master.file.task.search",
            path = "master-file/task/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<TaskDTO> searchTask(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchTasks(searchTerm);
    }

    @ApiMethod(
            name = "master.file.methodofrecording.search",
            path = "master-file/method-of-recording/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> searchMethodOfRecording(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchMasterfileByTypeAndName(MasterfileTypeEnum.METHOD_OF_RECORDING, searchTerm);
    }

    @ApiMethod(
            name = "master.file.proofofduty.search",
            path = "master-file/proof-of-duty/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> searchProofOfDuty(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchMasterfileByTypeAndName(MasterfileTypeEnum.PROOF_OF_DUTY, searchTerm);
    }
}
