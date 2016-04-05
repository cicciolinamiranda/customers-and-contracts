package com.g4s.javelin.api.location;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.g4s.javelin.api.location.MasterFileApi;
import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.dto.core.masterfile.ModeTransportDTO;
import com.g4s.javelin.dto.core.masterfile.SkillsDTO;
import com.g4s.javelin.dto.core.masterfile.TaskDTO;
import com.g4s.javelin.service.location.MasterFileService;

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