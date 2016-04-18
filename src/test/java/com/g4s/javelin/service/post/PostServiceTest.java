package com.g4s.javelin.service.post;

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
import junit.framework.Assert;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepositoryMock;

    @Mock
    private CustomerLocationRepository customerLocationRepositoryMock;

    @Mock
    private PostMasterfileAssociationService postMasterfileAssociationService;

    @InjectMocks
    private PostService postService = new PostServiceImpl();

    private List<PostModel> modelList = Lists.newArrayList();
    private MasterfileModel model;
    private List<LocationPostEquipmentModel> postEquipmentList = Lists.newArrayList();
    private List<EquipmentDTO> equipmentDtoList = Lists.newArrayList();
    private List<MasterfileDTO> healthSafetyRequirementsDTO = Lists.newArrayList();
    private List<MasterfileDTO> licensesDTO = Lists.newArrayList();
    private List<MasterfileDTO> skillsDTO = Lists.newArrayList();
    private List<MasterfileDTO> uniformsDTO = Lists.newArrayList();
    private PreferencesDTO preferencesDTO;
    private PostModel postModel;

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
        LocationPostEquipmentModel postEquipmentModel = new LocationPostEquipmentModel();
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
        skillsDTO.add(skills);
    }

    private void setUpUniformsDTO() {
        MasterfileDTO uniforms = new MasterfileDTO();
        uniforms.setId(1234l);
        uniforms.setName("Jumpsuit");
        uniformsDTO.add(uniforms);
    }

    private void setUpPostDTO() {
        PostDTO postDTO = new PostDTO();
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
        postDTO.setName("post");
    }

    @Test
    public void testGetPostByCustomerLocation() {
        when(postMasterfileAssociationService.getPostEquipments(Mockito.anyLong())).thenReturn(equipmentDtoList);
        when(postRepositoryMock.findByCustomerLocationId(Mockito.anyLong())).thenReturn(modelList);
        when(postRepositoryMock.save(postModel)).thenReturn(postModel);
        List<PostDTO> response = postService.getPostByCustomerLocation(24l);
        Assert.assertEquals(1, response.size());
    }

    @Test
    public void testGetPosDetails() {
        when(postRepositoryMock.findOne(Mockito.anyLong())).thenReturn(postModel);
        PostDTO result = postService.getPostDetails(24l);
        assertTrue(1L == result.getId());
    }

    @Test
    public void testSavePostDetails_newPost() throws PostException, PostDuplicateException {
        final PostDTO newPost = new PostDTO();

        final CustomerLocationModel customerLocationModel = new CustomerLocationModel();
        customerLocationModel.setId(1L);

        final PostModel postModel = new PostModel();
        postModel.setId(1L);

        when(customerLocationRepositoryMock.findOne(1L)).thenReturn(customerLocationModel);
        when(postRepositoryMock.save(any(PostModel.class))).thenReturn(postModel);

        assertTrue(postService.savePostDetails(newPost).getId() == 1L);
    }

    @Test(expected = PostException.class)
    public void testSavePostDetails_newPostThrowsPostException() throws PostException, PostDuplicateException {
        final PostDTO newPost = new PostDTO();

        final CustomerLocationModel customerLocationModel = new CustomerLocationModel();
        customerLocationModel.setId(1L);

        when(customerLocationRepositoryMock.findOne(1L)).thenReturn(customerLocationModel);
        when(postRepositoryMock.save(any(PostModel.class))).thenThrow(new HibernateException("Exception occurred."));

        postService.savePostDetails(newPost);
    }

    @Test
    public void testSavePostDetails_existingPostWithDifferentPostName() throws PostException, PostDuplicateException {
        final PostDTO newPost = new PostDTO();
        newPost.setId(1L);
        newPost.setName("new");

        final PostModel existingPost = new PostModel();
        existingPost.setName("existing");

        final CustomerLocationModel customerLocationModel = new CustomerLocationModel();
        customerLocationModel.setId(1L);

        final PostModel postModel = new PostModel();
        postModel.setId(1L);

        when(postRepositoryMock.findOne(1L)).thenReturn(existingPost);
        when(customerLocationRepositoryMock.findOne(1L)).thenReturn(customerLocationModel);
        when(postRepositoryMock.save(any(PostModel.class))).thenReturn(postModel);

        assertNotNull(postService.savePostDetails(newPost));
    }

    @Test
    public void testSavePostDetails_duplicatePostWithNewPostName() throws PostException, PostDuplicateException {
        final String newDuplicatePostName = "dupe-new";
        final String existingDuplicatePostName = "dupe-existing";

        final PostDTO newPost = new PostDTO();
        newPost.setName(newDuplicatePostName);

        final PostModel existingPost = new PostModel();
        existingPost.setName(existingDuplicatePostName);

        final CustomerLocationModel customerLocationModel = new CustomerLocationModel();
        customerLocationModel.setId(1L);

        final PostModel postModel = new PostModel();
        postModel.setId(1L);

        when(postRepositoryMock.findByName(existingDuplicatePostName)).thenReturn(existingPost);
        when(customerLocationRepositoryMock.findOne(1L)).thenReturn(customerLocationModel);
        when(postRepositoryMock.save(any(PostModel.class))).thenReturn(postModel);

        assertTrue(postService.savePostDetails(newPost).getName().equals(newDuplicatePostName));
    }
}
