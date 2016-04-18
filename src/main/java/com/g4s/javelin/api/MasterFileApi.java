package com.g4s.javelin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;
import com.g4s.javelin.dto.core.masterfile.TaskDTO;
import com.g4s.javelin.enums.MasterfileTypeEnum;
import com.g4s.javelin.service.masterfile.MasterfileService;
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
            name = "workorder.master.file.equipment.list",
            path = "workorder/master-file/equipment",
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
            name = "workorder.master.file.transport.list",
            path = "workorder/master-file/transport",
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
            name = "workorder.master.file.skills.list",
            path = "workorder/master-file/skills",
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
            name = "workorder.master.file.proofofduty.list",
            path = "workorder/master-file/proof-of-duty",
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
            name = "workorder.master.file.methodofrecording.list",
            path = "workorder/master-file/method-of-recording",
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
            name = "workorder.master.file.tasks.list",
            path = "workorder/master-file/tasks",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<TaskDTO> getAllTasks() {
        return masterFileService.getAllTasks();
    }

    @ApiMethod(
            name = "workorder.master.file.tasks.search",
            path = "workorder/master-file/tasks/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<TaskDTO> searchTasks(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchTasks(searchTerm);
    }

    @ApiMethod(
            name = "workorder.master.file.skills.search",
            path = "workorder/master-file/skills/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> searchSkills(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchMasterfileByTypeAndName(MasterfileTypeEnum.LOCATION_SKILLS, searchTerm);
    }

    @ApiMethod(
            name = "workorder.master.file.transport.search",
            path = "workorder/master-file/transport/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> searchTransport(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchMasterfileByTypeAndName(MasterfileTypeEnum.MODE_TRANSPORT, searchTerm);
    }

    @ApiMethod(
            name = "workorder.master.file.task.search",
            path = "workorder/master-file/task/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<TaskDTO> searchTask(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchTasks(searchTerm);
    }

    @ApiMethod(
            name = "workorder.master.file.methodofrecording.search",
            path = "workorder/master-file/method-of-recording/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> searchMethodOfRecording(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchMasterfileByTypeAndName(MasterfileTypeEnum.METHOD_OF_RECORDING, searchTerm);
    }

    @ApiMethod(
            name = "workorder.master.file.proofofduty.search",
            path = "workorder/master-file/proof-of-duty/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> searchProofOfDuty(@Named("searchTerm") final String searchTerm) {
        return masterFileService.searchMasterfileByTypeAndName(MasterfileTypeEnum.PROOF_OF_DUTY, searchTerm);
    }

    /**
     * Retrieve all gender
     *
     * @return List of all available gender
     */
    @ApiMethod(
            name = "workorder.master.file.gender.list",
            path = "workorder/master-file/gender",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllGender() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.GENDER);
    }

    /**
     * Retrieve all languages
     *
     * @return List of all available gender
     */
    @ApiMethod(
            name = "workorder.master.file.language.list",
            path = "workorder/master-file/language",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllLanguages() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.LANGUAGE);
    }

    /**
     * Retrieve all licenses
     *
     * @return List of all available licenses
     */
    @ApiMethod(
            name = "workorder.master.file.license.list",
            path = "workorder/master-file/license",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllLicenses() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.LICENSE);
    }

    /**
     * Retrieve all physical conditions
     *
     * @return List of all available physical conditions
     */
    @ApiMethod(
            name = "workorder.master.file.physicalcondition.list",
            path = "workorder/master-file/physical-condition",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllPhysicalConditions() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.PHYSICAL_CONDITION);
    }

    /**
     * Retrieve all post equipment
     *
     * @return List of all available post equipment
     */
    @ApiMethod(
            name = "workorder.master.file.postequipment.list",
            path = "workorder/master-file/post-equipment",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllPostEquipments() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.POST_EQUIPMENT);
    }

    /**
     * Retrieve all skills
     *
     * @return List of all available skills
     */
    @ApiMethod(
            name = "workorder.master.file.postskills.list",
            path = "workorder/master-file/post-skills",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllPostSkills() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.POST_SKILLS);
    }

    /**
     * Retrieve all qualifications
     *
     * @return List of all available qualifications
     */
    @ApiMethod(
            name = "workorder.master.file.qualification.list",
            path = "workorder/master-file/qualification",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllQualifications() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.QUALIFICATION);
    }

    /**
     * Retrieve all religions
     *
     * @return List of all available religions
     */
    @ApiMethod(
            name = "workorder.master.file.religion.list",
            path = "workorder/master-file/religion",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllReligions() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.RELIGION);
    }

    /**
     * Retrieve all role
     *
     * @return List of all available role
     */
    @ApiMethod(
            name = "workorder.master.file.role.list",
            path = "workorder/master-file/role",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllRoles() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.ROLE);
    }

    /**
     * Retrieve all trainings
     *
     * @return List of all available trainings
     */
    @ApiMethod(
            name = "workorder.master.file.training.list",
            path = "workorder/master-file/training",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllTrainings() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.TRAINING);
    }

    /**
     * Retrieve all uniforms
     *
     * @return List of all available uniforms
     */
    @ApiMethod(
            name = "workorder.master.file.uniform.list",
            path = "workorder/master-file/uniform",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllUniforms() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.UNIFORM);
    }

    /**
     * Retrieve all call in frequency
     *
     * @return List of all available call in frequency
     */
    @ApiMethod(
            name = "workorder.master.file.callfrequency.list",
            path = "workorder/master-file/call-frequency",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllCallFrequency() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.CALL_IN_FREQUENCY);
    }

    /**
     * Retrieve all call in frequency
     *
     * @return List of all available call in frequency
     */
    @ApiMethod(
            name = "workorder.master.file.healthsafetyrequirement.list",
            path = "workorder/master-file/health-safety-requirement",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllHealthSafetyRequirements() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.HEALTH_SAFETY_REQUIREMENTS);
    }

    /**
     * Retrieve all post allowances
     *
     * @return List of all available post allowances
     */
    @ApiMethod(
            name = "workorder.master.file.postallowances.list",
            path = "workorder/master-file/postallowances",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<MasterfileDTO> getAllPostAllowances() {
        return masterFileService.getMasterfilesByType(MasterfileTypeEnum.POST_ALLOWANCES);
    }
}
