package com.g4s.javelin.api.location;

import com.g4s.javelin.dto.core.location.EquipmentDTO;
import com.g4s.javelin.dto.core.location.ModeTransportDTO;
import com.g4s.javelin.dto.core.location.SkillsDTO;
import com.g4s.javelin.dto.core.location.TaskDTO;
import com.g4s.javelin.service.location.MasterFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Jordan Duabe
 * @since 03/29/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MasterFileApiTest {

    @Mock
    private MasterFileService masterFileService;

    @Mock
    private List<EquipmentDTO> equipmentList;

    @Mock
    private List<ModeTransportDTO> modeOfTransportList;

    @Mock
    private List<SkillsDTO> skillList;

    @Mock
    private List<TaskDTO> taskList;

    @InjectMocks
    private MasterFileApi masterFileApi = new MasterFileApi();

    @Test
    public void testGetAllEquipments() throws Exception {
        Mockito.when(masterFileService.getAllEquipments()).thenReturn(equipmentList);
        assertNotNull(masterFileApi.getAllEquipments());
    }

    @Test
    public void testGetAllModesOfTransport() throws Exception {
        Mockito.when(masterFileService.getAllModeTransport()).thenReturn(modeOfTransportList);
        assertNotNull(masterFileApi.getAllModesOfTransport());
    }

    @Test
    public void testGetAllSkills() throws Exception {
        Mockito.when(masterFileService.getAllSkills()).thenReturn(skillList);
        assertNotNull(masterFileApi.getAllSkills());
    }

    @Test
    public void testGetAllTasks() throws Exception {
        Mockito.when(masterFileService.getAllTasks()).thenReturn(taskList);
        assertNotNull(masterFileApi.getAllTasks());
    }
}