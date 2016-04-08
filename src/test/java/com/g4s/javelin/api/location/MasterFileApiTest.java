package com.g4s.javelin.api.location;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.g4s.javelin.api.MasterFileApi;
import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;
import com.g4s.javelin.dto.core.masterfile.TaskDTO;
import com.g4s.javelin.enums.MasterfileTypeEnum;
import com.g4s.javelin.service.masterfile.MasterfileService;

/**
 * @author Jordan Duabe
 * @since 03/29/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MasterFileApiTest {

    @Mock
    private MasterfileService masterFileService;

    @Mock
    private List<MasterfileDTO> equipmentList;

    @Mock
    private List<MasterfileDTO> modeOfTransportList;

    @Mock
    private List<MasterfileDTO> skillList;

    @Mock
    private List<TaskDTO> taskList;

    @InjectMocks
    private MasterFileApi masterFileApi = new MasterFileApi();

    @Test
    public void testGetAllEquipments() throws Exception {
        Mockito.when(masterFileService.getMasterfilesByType(MasterfileTypeEnum.LOCATION_EQUIPMENT)).thenReturn(equipmentList);
        assertNotNull(masterFileApi.getAllEquipments());
    }

    @Test
    public void testGetAllModesOfTransport() throws Exception {
        Mockito.when(masterFileService.getMasterfilesByType(MasterfileTypeEnum.MODE_TRANSPORT)).thenReturn(modeOfTransportList);
        assertNotNull(masterFileApi.getAllModesOfTransport());
    }

    @Test
    public void testGetAllSkills() throws Exception {
        Mockito.when(masterFileService.getMasterfilesByType(MasterfileTypeEnum.LOCATION_SKILLS)).thenReturn(skillList);
        assertNotNull(masterFileApi.getAllSkills());
    }

    @Test
    public void testGetAllTasks() throws Exception {
        Mockito.when(masterFileService.getAllTasks()).thenReturn(taskList);
        assertNotNull(masterFileApi.getAllTasks());
    }
}