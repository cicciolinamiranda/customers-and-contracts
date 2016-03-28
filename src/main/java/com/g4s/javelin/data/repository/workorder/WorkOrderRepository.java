package com.g4s.javelin.data.repository.workorder;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.workorder.WorkOrderModel;

public interface WorkOrderRepository extends JpaRepository<WorkOrderModel, Long> {

}
