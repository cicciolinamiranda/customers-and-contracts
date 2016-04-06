package com.g4s.javelin.data.service.location;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

import org.hibernate.MultiTenancyStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.g4s.javelin.data.model.masterfile.MasterfileModel;
import com.g4s.javelin.data.model.masterfile.TaskActivityModel;
import com.g4s.javelin.data.model.masterfile.TaskModel;
import com.g4s.javelin.data.repository.masterfile.MasterfileRepository;
import com.g4s.javelin.data.repository.masterfile.TaskRepository;
import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;
import com.g4s.javelin.dto.core.masterfile.ModeTransportDTO;
import com.g4s.javelin.dto.core.masterfile.TaskDTO;
import com.g4s.javelin.enums.MasterfileTypeEnum;
import com.g4s.javelin.service.masterfile.MasterfileService;
import com.g4s.javelin.service.masterfile.impl.MasterfileServiceImpl;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.google.appengine.repackaged.com.google.api.client.util.Sets;

@RunWith(MockitoJUnitRunner.class)
public class MasterFileServiceTest {

    @Mock
    private MasterfileRepository masterfileRepository;

    @Mock
    private TaskRepository taskRepositoryMock;

    @InjectMocks
    private MasterfileService masterFileService = new MasterfileServiceImpl();

    private MasterfileModel equipmentModel;

    private MasterfileModel modeTransportModel;

    private MasterfileModel skillsModel;

    private TaskModel taskModel;

    @Before
    public void initMocks() {
        setUpEquipmentModel();
        setUpModeTransportModel();
        setUpSkillsModel();
        setUpTaskModel();
    }

    private void setUpEquipmentModel() {
        equipmentModel = new MasterfileModel();
        equipmentModel.setId(1234l);
        equipmentModel.setName("Gun");
    }

    private void setUpModeTransportModel() {
        modeTransportModel = new MasterfileModel();
        modeTransportModel.setId(1234l);
        modeTransportModel.setName("Car");
    }

    private void setUpSkillsModel() {
        skillsModel = new MasterfileModel();
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
        List<MasterfileModel> expectedList = Lists.newArrayList();
        expectedList.add(equipmentModel);
        when(masterfileRepository.findByType(MasterfileTypeEnum.LOCATION_EQUIPMENT)).thenReturn(expectedList);
        List<MasterfileDTO> results = masterFileService.getMasterfilesByType(MasterfileTypeEnum.LOCATION_EQUIPMENT);
        verify(masterfileRepository, times(1)).findByType(MasterfileTypeEnum.LOCATION_EQUIPMENT);
        assertEquals(1, results.size());
        assertEquals("Gun", results.get(0).getName());
    }

    @Test
    public void testGetAllModeTransport() {
        List<MasterfileModel> expectedList = Lists.newArrayList();
        expectedList.add(modeTransportModel);
        when(masterfileRepository.findByType(MasterfileTypeEnum.MODE_TRANSPORT)).thenReturn(expectedList);
        List<MasterfileDTO> results = masterFileService
                .getMasterfilesByType(MasterfileTypeEnum.MODE_TRANSPORT);
        verify(masterfileRepository, times(1)).findByType(MasterfileTypeEnum.MODE_TRANSPORT);
        assertEquals(1, results.size());
        assertEquals("Car", results.get(0).getName());
    }

    @Test
    public void testGetAllSkills() {
        List<MasterfileModel> expectedList = Lists.newArrayList();
        expectedList.add(skillsModel);
        when(masterfileRepository.findByType(MasterfileTypeEnum.LOCATION_SKILLS)).thenReturn(expectedList);
        List<MasterfileDTO> results = masterFileService.getMasterfilesByType(MasterfileTypeEnum.LOCATION_SKILLS);
        verify(masterfileRepository, times(1)).findByType(MasterfileTypeEnum.LOCATION_SKILLS);
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
