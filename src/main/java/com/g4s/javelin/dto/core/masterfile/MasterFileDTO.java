package com.g4s.javelin.dto.core.masterfile;

import java.util.List;

public class MasterFileDTO {

    List<EquipmentDTO> equipments;
    List<SkillsDTO> skills;
    List<TaskDTO> tasks;
    List<ModeTransportDTO> modeTransport;
    public List<EquipmentDTO> getEquipments() {
        return equipments;
    }
    public void setEquipments(final List<EquipmentDTO> equipments) {
        this.equipments = equipments;
    }
    public List<SkillsDTO> getSkills() {
        return skills;
    }
    public void setSkills(final List<SkillsDTO> skills) {
        this.skills = skills;
    }
    public List<TaskDTO> getTasks() {
        return tasks;
    }
    public void setTasks(final List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
    public List<ModeTransportDTO> getModeTransport() {
        return modeTransport;
    }
    public void setModeTransport(final List<ModeTransportDTO> modeTransport) {
        this.modeTransport = modeTransport;
    }

}
