package com.g4s.javelin.api.location;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.g4s.javelin.api.PostApi;
import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;
import com.g4s.javelin.dto.core.post.PostDTO;
import com.g4s.javelin.dto.core.post.PreferencesDTO;
import com.g4s.javelin.exception.PostDuplicateException;
import com.g4s.javelin.exception.PostException;
import com.g4s.javelin.service.post.PostService;
import com.google.common.collect.Lists;

/**
 * @author cicc
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PostApiTest {

    @Mock
    private PostService postServiceMock;
    
    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private PostApi postApiMock = new PostApi();

    private PostDTO postDTO;
    private List<EquipmentDTO> equipmentDtoList = Lists.newArrayList();
    private List<MasterfileDTO> healthSafetyRequirementsDTO = Lists.newArrayList();
    private List<MasterfileDTO> licensesDTO = Lists.newArrayList();
    private List<MasterfileDTO> skillsDTO = Lists.newArrayList();
    private List<MasterfileDTO> uniformsDTO = Lists.newArrayList();
    private PreferencesDTO preferencesDTO;

    @Before
    public void init() {
        setUpEquipmentDTO();
        setUpHealthSafetyRequirementsDTO();
        setUpLicensesDTO();
        setUpPreferencesDTO();
        setUpSkillsDTO();
        setUpUniformsDTO();
        setUpPostDTO();
    }

    private void setUpEquipmentDTO() {
        EquipmentDTO eq = new EquipmentDTO();
        eq.setAssociationId(1L);
        eq.setId(1L);
        eq.setName("Post Equipment 1");
        eq.setQuantity(99);
        equipmentDtoList.add(eq);
    }

    private void setUpHealthSafetyRequirementsDTO() {
        MasterfileDTO healthSafetyRequirements = new MasterfileDTO();
        healthSafetyRequirements.setId(1234l);
        healthSafetyRequirements.setName("Medical Records");
        healthSafetyRequirementsDTO.add(healthSafetyRequirements);
    }

    private void setUpLicensesDTO() {
        MasterfileDTO licenses = new MasterfileDTO();
        licenses.setId(1234l);
        licenses.setName("Driver's License");
        licensesDTO.add(licenses);
    }

    private void setUpPreferencesDTO() {
        preferencesDTO = new PreferencesDTO();
        MasterfileDTO gender = new MasterfileDTO();
        gender.setId(1234l);
        gender.setName("Female");
        preferencesDTO.setGender(gender);
        preferencesDTO.setHeight(45l);
        MasterfileDTO language = new MasterfileDTO();
        language.setId(1234l);
        language.setName("English");
        List<MasterfileDTO> languageList = Lists.newArrayList();
        languageList.add(language);
        preferencesDTO.setLanguages(languageList);
        MasterfileDTO physicalCondition = new MasterfileDTO();
        physicalCondition.setId(1234l);
        physicalCondition.setName("Skin Allergy");
        List<MasterfileDTO> physicalConditions = Lists.newArrayList();
        physicalConditions.add(physicalCondition);
        preferencesDTO.setPhysicalConditions(physicalConditions);
    }

    private void setUpSkillsDTO() {
        MasterfileDTO skills = new MasterfileDTO();
        skills.setId(1234l);
        skills.setName("Martial arts");
        skillsDTO.add(skills );
    }

    private void setUpUniformsDTO() {
        MasterfileDTO uniforms = new MasterfileDTO();
        uniforms.setId(1234l);
        uniforms.setName("Jumpsuit");
        uniformsDTO.add(uniforms );
    }

    private void setUpPostDTO() {
        postDTO = new PostDTO();
        postDTO.setId(1L);
        postDTO.setEquipments(equipmentDtoList);
        postDTO.setHealthSafetyRequirements(healthSafetyRequirementsDTO);
        postDTO.setIdentificationNumber("0001-01-01");
        postDTO.setCustomerLocationId(24l);
        postDTO.setIdentificationRequired(true);
        postDTO.setLicenses(licensesDTO);
        postDTO.setPreferences(preferencesDTO);
        postDTO.setSkills(skillsDTO);
        postDTO.setUniforms(uniformsDTO);
        postDTO.setPostCover("1");
    }

    /**
     * 
     */
    @Test
    public void testGetPostDetails() {
        when(postServiceMock.getPostDetails(Mockito.anyLong())).thenReturn(postDTO);
        assertNotNull(postApiMock.getPostDetails(1L));
    }

    @Test
    public void testListPostDetails() {
        List<PostDTO> postDTOList = Lists.newArrayList();
        postDTOList.add(postDTO);
        when(postServiceMock.getPostByCustomerLocation(Mockito.anyLong())).thenReturn(postDTOList);
        assertNotNull(postApiMock.listPostDetails(1L));
    }

    @Test
    public void testSavePostDetails() throws PostException, PostDuplicateException {
        when(postServiceMock.savePostDetails(postDTO)).thenReturn(postDTO);
        assertNotNull(postApiMock.savePostDetails(postDTO, request));
    }

    @Test
    public void testUpdatePostDetails() throws PostException, PostDuplicateException {
        when(postServiceMock.savePostDetails(postDTO)).thenReturn(postDTO);
        assertNotNull(postApiMock.savePostDetails(postDTO, request));
    }
}
