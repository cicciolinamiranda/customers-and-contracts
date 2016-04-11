package com.g4s.javelin.service.post;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.data.model.masterfile.MasterfileModel;
import com.g4s.javelin.data.model.post.LocationPostEquipmentModel;
import com.g4s.javelin.data.model.post.PostModel;
import com.g4s.javelin.data.repository.location.CustomerLocationRepository;
import com.g4s.javelin.data.repository.post.PostRepository;
import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;
import com.g4s.javelin.dto.core.post.PostDTO;
import com.g4s.javelin.dto.core.post.PreferencesDTO;
import com.g4s.javelin.enums.MasterfileTypeEnum;
import com.g4s.javelin.exception.PostDuplicateException;
import com.g4s.javelin.exception.PostException;
import com.g4s.javelin.service.post.impl.PostServiceImpl;
import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepositoryMock;

    @Mock
    private CustomerLocationRepository customerLocationRepositoryMock;

    @Mock
    private PostMasterfileAssociationService postMasterfileAssociationService;
    @InjectMocks
    private PostService postServiceMock = new PostServiceImpl();
    private List<PostModel> modelList  = Lists.newArrayList();
    private MasterfileModel model;
    private LocationPostEquipmentModel postEquipmentModel;
    private List<LocationPostEquipmentModel> postEquipmentList = Lists.newArrayList();
    private List<EquipmentDTO> equipmentDtoList = Lists.newArrayList();
    private List<MasterfileDTO> healthSafetyRequirementsDTO = Lists.newArrayList();
    private List<MasterfileDTO> licensesDTO = Lists.newArrayList();
    private List<MasterfileDTO> skillsDTO = Lists.newArrayList();
    private List<MasterfileDTO> uniformsDTO = Lists.newArrayList();
    private PreferencesDTO preferencesDTO;
    private PostModel postModel;
    private PostDTO postDTO;

    @Before
    public void init() {
        setUpPost();
        setUpEquipmentDTO();
        setUpMasterfileModel();
        setUpPostModelList();
        setUpPostEquipmentModel();
        setUpHealthSafetyRequirementsDTO();
        setUpLicensesDTO();
        setUpPreferencesDTO();
        setUpSkillsDTO();
        setUpUniformsDTO();
        setUpPostDTO();
    }

    private void setUpMasterfileModel() {
        model = new MasterfileModel();
        model.setId(1L);
        model.setType(MasterfileTypeEnum.POST_EQUIPMENT);
        model.setName("Post Equipment 1");
    }

    private void setUpPost() {
        postModel = new PostModel();
        postModel.setId(1L);
    }

    private void setUpPostModelList() {
        modelList.add(postModel);
    }

    private void setUpPostEquipmentModel() {
        postEquipmentModel = new LocationPostEquipmentModel();
        postEquipmentModel.setEquipment(model);
        postEquipmentModel.setPost(postModel);
        postEquipmentModel.setQuantity(99);
        postEquipmentModel.setId(1L);
        postEquipmentList.add(postEquipmentModel);

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

    @Test
    public void testGetPostByCustomerLocation() {
        when(postMasterfileAssociationService.getPostEquipments(Mockito.anyLong())).thenReturn(equipmentDtoList);
        when(postRepositoryMock.findByCustomerLocationId(Mockito.anyLong())).thenReturn(modelList);
        when(postRepositoryMock.save(postModel)).thenReturn(postModel);
        List<PostDTO> response = postServiceMock.getPostByCustomerLocation(24l);
        Assert.assertEquals(1, response.size());
    }

    @Test
    public void testGetPosDetails() {
        when(postRepositoryMock.findOne(Mockito.anyLong())).thenReturn(postModel);
        PostDTO result = postServiceMock.getPostDetails(24l);
        assertTrue(1L == result.getId());
    }

    @Test
    public void testSavePostDetails() throws PostException, PostDuplicateException {
        when(postRepositoryMock.findByName(Mockito.anyString())).thenReturn(postModel);
        CustomerLocationModel custModel = new CustomerLocationModel();
        custModel.setId(1l);
        when(customerLocationRepositoryMock.findOne(1L)).thenReturn(custModel);
        when(postRepositoryMock.save(postModel)).thenReturn(postModel);
        PostDTO result = postServiceMock.savePostDetails(postDTO);
        assertTrue(1L == result.getId());
    }
}
