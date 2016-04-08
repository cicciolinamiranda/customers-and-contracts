package com.g4s.javelin.service.post;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.g4s.javelin.data.model.masterfile.MasterfileModel;
import com.g4s.javelin.data.model.post.LocationPostEquipmentModel;
import com.g4s.javelin.data.model.post.PostModel;
import com.g4s.javelin.data.repository.masterfile.MasterfileRepository;
import com.g4s.javelin.data.repository.post.LocationPostEquipmentRepository;
import com.g4s.javelin.data.repository.post.PostRepository;
import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.enums.MasterfileTypeEnum;
import com.g4s.javelin.service.post.impl.PostMasterfileAssociationServiceImpl;
import com.google.common.collect.Lists;
@RunWith(MockitoJUnitRunner.class)
public class PostMasterfileAssociationServiceTest {

    @Mock
    private MasterfileRepository masterfileRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private LocationPostEquipmentRepository postEquipmentRepository;

    @InjectMocks
    private PostMasterfileAssociationService service = new PostMasterfileAssociationServiceImpl();
 
    MasterfileModel model;
    LocationPostEquipmentModel postEquipmentModel;
    List<LocationPostEquipmentModel> postEquipmentList = Lists.newArrayList();
    List<EquipmentDTO> dto = Lists.newArrayList();

    PostModel post;
    @Before
    public void init() {
        setUpPost();
        setUpEquipmentDTO();
        setUpMasterfileModel();
        setUpPostEquipmentModel();
    }

    private void setUpMasterfileModel() {
        model = new MasterfileModel();
        model.setId(1L);
        model.setType(MasterfileTypeEnum.POST_EQUIPMENT);
        model.setName("Post Equipment 1");
    }

    private void setUpPostEquipmentModel() {
        postEquipmentModel = new LocationPostEquipmentModel();
        postEquipmentModel.setEquipment(model);
        postEquipmentModel.setPost(post);
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
        dto.add(eq);
    }

    private void setUpPost() {
    	post = new PostModel();
    	post.setId(1L);
    }

    @Test
    public void testSavePostEquipment() {
    	EquipmentDTO eq = new EquipmentDTO();
    	eq.setDeleted(true);
    	eq.setAssociationId(1L);
    	dto.add(eq);
        when(postRepository.findOne(1L)).thenReturn(post);
        when(masterfileRepository.findOne(Mockito.anyLong())).thenReturn(model);
        service.savePostEquipment(Mockito.anyLong(), dto);
        verify(postEquipmentRepository, times(1)).delete(1L);
    }

    @Test
    public void testGetPostEquipments() {
    	EquipmentDTO eq = new EquipmentDTO();
    	eq.setDeleted(true);
    	eq.setAssociationId(1L);
    	dto.add(eq);
        when(postEquipmentRepository.findByPostId(Mockito.anyLong())).thenReturn(postEquipmentList);
        List<EquipmentDTO> response = service.getPostEquipments(1L);
        Assert.assertEquals(1, response.size());
    }
}
