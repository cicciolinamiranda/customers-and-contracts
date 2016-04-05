package com.g4s.javelin.data.service.location;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.g4s.javelin.data.model.masterfile.EquipmentModel;
import com.g4s.javelin.data.model.masterfile.ModeTransportModel;
import com.g4s.javelin.data.model.masterfile.SkillsModel;
import com.g4s.javelin.data.model.masterfile.TaskActivityModel;
import com.g4s.javelin.data.model.masterfile.TaskModel;
import com.g4s.javelin.data.repository.masterfile.EquipmentRepository;
import com.g4s.javelin.data.repository.masterfile.ModeTransportRepository;
import com.g4s.javelin.data.repository.masterfile.SkillsRepository;
import com.g4s.javelin.data.repository.masterfile.TaskRepository;
import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.dto.core.masterfile.ModeTransportDTO;
import com.g4s.javelin.dto.core.masterfile.SkillsDTO;
import com.g4s.javelin.dto.core.masterfile.TaskDTO;
import com.g4s.javelin.service.location.MasterFileService;
import com.g4s.javelin.service.location.impl.MasterFileServiceImpl;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.google.appengine.repackaged.com.google.api.client.util.Sets;

@RunWith(MockitoJUnitRunner.class)
public class MasterFileServiceTest {

    @Mock
    private EquipmentRepository equipmentRepositoryMock;

    @Mock
    private ModeTransportRepository modeTransportRepositoryMock;

    @Mock
    private SkillsRepository skillsRepositoryMock;

    @Mock
    private TaskRepository taskRepositoryMock;

    @InjectMocks
    private MasterFileService masterFileService = new MasterFileServiceImpl();

    private EquipmentModel equipmentModel;

    private ModeTransportModel modeTransportModel;

    private SkillsModel skillsModel;

    private TaskModel taskModel;

    @Before
    public void initMocks() {
        setUpEquipmentModel();
        setUpModeTransportModel();
        setUpSkillsModel();
        setUpTaskModel();
    }

    private void setUpEquipmentModel() {
        equipmentModel = new EquipmentModel();
        equipmentModel.setId(1234l);
        equipmentModel.setName("Gun");
    }

    private void setUpModeTransportModel() {
        modeTransportModel = new ModeTransportModel();
        modeTransportModel.setId(1234l);
        modeTransportModel.setName("Car");
    }

    private void setUpSkillsModel() {
        skillsModel = new SkillsModel();
        skillsModel.setId(1234l);
        skillsModel.setName("Guard");
    }

    private void setUpTaskModel() {
        taskModel = new TaskModel();
        taskModel.setId(1234l);
        taskModel.setName("Guarding");
        TaskActivityModel taskActivityModel = new TaskActivityModel();
        taskActivityModel.setId(1234l);
        taskActivityModel.setName("Swiping");
        Set<TaskActivityModel> taskActivities = Sets.newHashSet();
        taskActivities.add(taskActivityModel);
        taskModel.setTaskActivities(taskActivities);
    }

    @Test
    public void testGetAllEquipments() {
        List<EquipmentModel> expectedList = Lists.newArrayList();
        expectedList.add(equipmentModel);
        when(equipmentRepositoryMock.findAll()).thenReturn(expectedList);
        List<EquipmentDTO> results = masterFileService.getAllEquipments();
        verify(equipmentRepositoryMock, times(1)).findAll();
        assertEquals(1, results.size());
        assertEquals("Gun", results.get(0).getName());
    }

    @Test
    public void testGetAllModeTransport() {
        List<ModeTransportModel> expectedList = Lists.newArrayList();
        expectedList.add(modeTransportModel);
        when(modeTransportRepositoryMock.findAll()).thenReturn(expectedList);
        List<ModeTransportDTO> results = masterFileService
                .getAllModeTransport();
        verify(modeTransportRepositoryMock, times(1)).findAll();
        assertEquals(1, results.size());
        assertEquals("Car", results.get(0).getName());
    }

    @Test
    public void testGetAllSkills() {
        List<SkillsModel> expectedList = Lists.newArrayList();
        expectedList.add(skillsModel);
        when(skillsRepositoryMock.findAll()).thenReturn(expectedList);
        List<SkillsDTO> results = masterFileService.getAllSkills();
        verify(skillsRepositoryMock, times(1)).findAll();
        assertEquals(1, results.size());
        assertEquals("Guard", results.get(0).getName());
    }

    @Test
    public void testGetAllTasks() {
        List<TaskModel> expectedList = Lists.newArrayList();
        expectedList.add(taskModel);
        when(taskRepositoryMock.findAll()).thenReturn(expectedList);
        List<TaskDTO> results = masterFileService.getAllTasks();
        verify(taskRepositoryMock, times(1)).findAll();
        assertEquals(1, results.size());
        assertEquals("Guarding", results.get(0).getName());
        assertEquals(1, results.get(0).getTaskActivities().size());
        assertEquals("Swiping", results.get(0).getTaskActivities().get(0).getName());
    }
}
