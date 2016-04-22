package com.g4s.javelin.util;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.g4s.javelin.dto.core.audit.AuditLogDTO;
import com.g4s.javelin.dto.core.location.AddressDTO;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;
import com.g4s.javelin.dto.core.location.CustomerDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;
import com.g4s.javelin.dto.core.masterfile.ModeTransportDTO;
import com.g4s.javelin.dto.core.masterfile.TaskDTO;
import com.g4s.javelin.dto.core.post.PostDTO;
import com.g4s.javelin.dto.core.post.PreferencesDTO;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

public class AuditLogUtilTest {

    private CustomerLocationDTO newCustomerLocationDTO;
    private CustomerLocationDTO oldCustomerLocationDTO;
    private PostDTO oldPostDTO;
    private List<EquipmentDTO> oldEquipmentDtoList = Lists.newArrayList();
    private List<MasterfileDTO> oldHealthSafetyRequirementsDTO = Lists.newArrayList();
    private List<MasterfileDTO> oldLicensesDTO = Lists.newArrayList();
    private List<MasterfileDTO> oldSkillsDTO = Lists.newArrayList();
    private List<MasterfileDTO> oldUniformsDTO = Lists.newArrayList();
    private PreferencesDTO oldPreferencesDTO;
    private PostDTO newPostDTO;
    private List<EquipmentDTO> newEquipmentDtoList = Lists.newArrayList();
    private List<MasterfileDTO> newHealthSafetyRequirementsDTO = Lists.newArrayList();
    private List<MasterfileDTO> newLicensesDTO = Lists.newArrayList();
    private List<MasterfileDTO> newSkillsDTO = Lists.newArrayList();
    private List<MasterfileDTO> newUniformsDTO = Lists.newArrayList();
    private PreferencesDTO newPreferencesDTO;

    
    @Before
    public void initMocks() {
        setUpOldCustomerLocationDTO();
        setUpNewCustomerLocationDTO();
        setUpOldEquipmentDTO();
        setUpOldHealthSafetyRequirementsDTO();
        setUpOldLicensesDTO();
        setUpOldPreferencesDTO();
        setUpOldSkillsDTO();
        setUpOldUniformsDTO();
        setUpOldPostDTO();
        setUpNewEquipmentDTO();
        setUpNewHealthSafetyRequirementsDTO();
        setUpNewLicensesDTO();
        setUpNewPreferencesDTO();
        setUpNewSkillsDTO();
        setUpNewUniformsDTO();
        setUpNewPostDTO();
    }

    private void setUpOldEquipmentDTO() {
        EquipmentDTO eq = new EquipmentDTO();
        eq.setAssociationId(1L);
        eq.setId(1L);
        eq.setName("Post Equipment 2");
        eq.setQuantity(55);
        oldEquipmentDtoList.add(eq);
    }

    private void setUpOldHealthSafetyRequirementsDTO() {
        MasterfileDTO healthSafetyRequirements = new MasterfileDTO();
        healthSafetyRequirements.setId(1234l);
        healthSafetyRequirements.setName("Educational Records");
        oldHealthSafetyRequirementsDTO.add(healthSafetyRequirements);
    }

    private void setUpOldLicensesDTO() {
        MasterfileDTO licenses = new MasterfileDTO();
        licenses.setId(1234l);
        licenses.setName("Doctor's License");
        oldLicensesDTO.add(licenses);
    }

    private void setUpOldPreferencesDTO() {
        oldPreferencesDTO = new PreferencesDTO();
        MasterfileDTO gender = new MasterfileDTO();
        gender.setId(1234l);
        gender.setName("Make");
        oldPreferencesDTO.setGender(gender);
        oldPreferencesDTO.setHeight(55l);
        MasterfileDTO language = new MasterfileDTO();
        language.setId(1234l);
        language.setName("English");
        List<MasterfileDTO> languageList = Lists.newArrayList();
        languageList.add(language);
        oldPreferencesDTO.setLanguages(languageList);
        MasterfileDTO physicalCondition = new MasterfileDTO();
        physicalCondition.setId(1234l);
        physicalCondition.setName("Skin Allergy");
        List<MasterfileDTO> physicalConditions = Lists.newArrayList();
        physicalConditions.add(physicalCondition);
        oldPreferencesDTO.setPhysicalConditions(physicalConditions);
    }

    private void setUpOldSkillsDTO() {
        MasterfileDTO skills = new MasterfileDTO();
        skills.setId(1234l);
        skills.setName("Gun Shooting");
        oldSkillsDTO.add(skills);
        MasterfileDTO skills2 = new MasterfileDTO();
        skills.setId(1234l);
        skills.setName("Karate");
        oldSkillsDTO.add(skills2 );
    }

    private void setUpOldUniformsDTO() {
        MasterfileDTO uniforms = new MasterfileDTO();
        uniforms.setId(1234l);
        uniforms.setName("Jumpsuit");
        oldUniformsDTO.add(uniforms );
    }

    private void setUpOldPostDTO() {
        oldPostDTO = new PostDTO();
        oldPostDTO.setId(1L);
        oldPostDTO.setEquipments(oldEquipmentDtoList);
        oldPostDTO.setHealthSafetyRequirements(oldHealthSafetyRequirementsDTO);
        oldPostDTO.setIdentificationNumber("0001-01-01");
        oldPostDTO.setCustomerLocationId(24l);
        oldPostDTO.setIdentificationRequired(true);
        oldPostDTO.setLicenses(oldLicensesDTO);
        oldPostDTO.setPreferences(oldPreferencesDTO);
        oldPostDTO.setSkills(oldSkillsDTO);
        oldPostDTO.setUniforms(oldUniformsDTO);
        oldPostDTO.setPostCover("1");
    }

    private void setUpNewEquipmentDTO() {
        EquipmentDTO eq = new EquipmentDTO();
        eq.setAssociationId(1L);
        eq.setId(1L);
        eq.setName("Post Equipment 1");
        eq.setQuantity(99);
        newEquipmentDtoList.add(eq);
    }

    private void setUpNewHealthSafetyRequirementsDTO() {
        MasterfileDTO healthSafetyRequirements = new MasterfileDTO();
        healthSafetyRequirements.setId(1234l);
        healthSafetyRequirements.setName("Medical Records");
        newHealthSafetyRequirementsDTO.add(healthSafetyRequirements);
    }

    private void setUpNewLicensesDTO() {
        MasterfileDTO licenses = new MasterfileDTO();
        licenses.setId(1234l);
        licenses.setName("Driver's License");
        newLicensesDTO.add(licenses);
    }

    private void setUpNewPreferencesDTO() {
        newPreferencesDTO = new PreferencesDTO();
        MasterfileDTO gender = new MasterfileDTO();
        gender.setId(1234l);
        gender.setName("Female");
        newPreferencesDTO.setGender(gender);
        newPreferencesDTO.setHeight(45l);
        MasterfileDTO language = new MasterfileDTO();
        language.setId(1234l);
        language.setName("English");
        List<MasterfileDTO> languageList = Lists.newArrayList();
        languageList.add(language);
        newPreferencesDTO.setLanguages(languageList);
        MasterfileDTO physicalCondition = new MasterfileDTO();
        physicalCondition.setId(1234l);
        physicalCondition.setName("Skin Allergy");
        List<MasterfileDTO> physicalConditions = Lists.newArrayList();
        physicalConditions.add(physicalCondition);
        newPreferencesDTO.setPhysicalConditions(physicalConditions);
    }

    private void setUpNewSkillsDTO() {
        MasterfileDTO skills = new MasterfileDTO();
        skills.setId(1234l);
        skills.setName("Martial arts");
        newSkillsDTO.add(skills );
    }

    private void setUpNewUniformsDTO() {
        MasterfileDTO uniforms = new MasterfileDTO();
        uniforms.setId(1234l);
        uniforms.setName("Jumpsuit");
        newUniformsDTO.add(uniforms );
    }

    private void setUpNewPostDTO() {
        newPostDTO = new PostDTO();
        newPostDTO.setId(1L);
        newPostDTO.setEquipments(newEquipmentDtoList);
        newPostDTO.setHealthSafetyRequirements(newHealthSafetyRequirementsDTO);
        newPostDTO.setIdentificationNumber("0001-01-01");
        newPostDTO.setCustomerLocationId(24l);
        newPostDTO.setIdentificationRequired(true);
        newPostDTO.setLicenses(newLicensesDTO);
        newPostDTO.setPreferences(newPreferencesDTO);
        newPostDTO.setSkills(newSkillsDTO);
        newPostDTO.setUniforms(newUniformsDTO);
        newPostDTO.setPostCover("1");
    }

    private void setUpOldCustomerLocationDTO() {
        oldCustomerLocationDTO = new CustomerLocationDTO();
        oldCustomerLocationDTO.setId(1L);
        oldCustomerLocationDTO.setIpAddress("1.1.1.1");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddress("1234 Jupiter Street, Manila");
        addressDTO.setLatitude("0");
        addressDTO.setLongitude("0");
        addressDTO.setPostCode("1234");
        oldCustomerLocationDTO.setAddress(addressDTO);
        List<BarredEmployeeDTO> barredEmployees = Lists.newArrayList();
        BarredEmployeeDTO barredEmployeeDTO = new BarredEmployeeDTO();
        barredEmployeeDTO.setEmployeeId(1234l);
        barredEmployeeDTO.setFirstName("Juan");
        barredEmployeeDTO.setLastName("Dela Cruz");
        barredEmployees.add(barredEmployeeDTO);
        oldCustomerLocationDTO.setBarredEmployees(barredEmployees);
        oldCustomerLocationDTO.setCreatedDate(new DateTime());
        List<EquipmentDTO> equipments = Lists.newArrayList();
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setName("Gun");
        equipments.add(equipmentDTO);
        oldCustomerLocationDTO.setEquipments(equipments);
        oldCustomerLocationDTO.setFloorPlan("Floor Plan");
        CustomerDTO customer = new CustomerDTO();
        customer.setCustomerName("Juan Dela Cruz");
         List<ModeTransportDTO> modeTransports = new
         ArrayList<ModeTransportDTO>();
         ModeTransportDTO modeTransport = new ModeTransportDTO();
         modeTransport.setName("Armored Truck");
         modeTransports.add(modeTransport );
         oldCustomerLocationDTO.setModeOfTransports(modeTransports);
         List<MasterfileDTO> skills = Lists.newArrayList();
         oldCustomerLocationDTO.setSkills(skills);
         List<TaskDTO> tasks = Lists.newArrayList();
         oldCustomerLocationDTO.setTasks(tasks);
         oldCustomerLocationDTO.setCustomer(customer);
    }

    private void setUpNewCustomerLocationDTO() {
        newCustomerLocationDTO = new CustomerLocationDTO();
        newCustomerLocationDTO.setId(1L);
        newCustomerLocationDTO.setIpAddress("1.1.1.1");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddress("1234 CityLand, Manila");
        addressDTO.setLatitude("1");
        addressDTO.setLongitude("1");
        addressDTO.setPostCode("1111");
        newCustomerLocationDTO.setAddress(addressDTO);
        List<BarredEmployeeDTO> barredEmployees = Lists.newArrayList();
        BarredEmployeeDTO barredEmployeeDTO = new BarredEmployeeDTO();
        barredEmployeeDTO.setEmployeeId(1234l);
        barredEmployeeDTO.setFirstName("Cicci");
        barredEmployeeDTO.setLastName("Dela Cruz");
        barredEmployees.add(barredEmployeeDTO);
        newCustomerLocationDTO.setBarredEmployees(barredEmployees);
        newCustomerLocationDTO.setCreatedDate(new DateTime());
        List<EquipmentDTO> equipments = Lists.newArrayList();
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setName("Sword");
        equipments.add(equipmentDTO);
        newCustomerLocationDTO.setEquipments(equipments);
        newCustomerLocationDTO.setFloorPlan("Floor Plan 4");
        CustomerDTO customer = new CustomerDTO();
        customer.setCustomerName("JP Vales");
         List<ModeTransportDTO> modeTransports = Lists.newArrayList();
         ModeTransportDTO modeTransport = new ModeTransportDTO();
         modeTransport.setName("Armored Car");
         modeTransports.add(modeTransport );
         newCustomerLocationDTO.setModeOfTransports(modeTransports);
         List<MasterfileDTO> skills = Lists.newArrayList();
         newCustomerLocationDTO.setSkills(skills);
         List<TaskDTO> tasks = Lists.newArrayList();
         newCustomerLocationDTO.setTasks(tasks);
         newCustomerLocationDTO.setCustomer(customer);
    }

    @Ignore
    @Test
    public void testCustomerLocationGetOldNewValue() throws IOException {
    }

    @Ignore
    @Test
    public void testPostGetOldNewValue() throws IOException {
    }
}
