package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.enums.StatusEnum;

public interface CustomerLocationRepository extends
        JpaRepository<CustomerLocationModel, Long> {

    List<CustomerLocationModel> findByWorkOrdersId(final Long id);

    List<CustomerLocationModel> findByAddressAddressContainingIgnoreCase(final String address);

    List<CustomerLocationModel> findByCustomerCustomerNameContainingIgnoreCase(final String customerName);

    @Modifying
    @Transactional
    @Query("UPDATE CustomerLocationModel SET status= :status WHERE id=:id")
    void updateStatus(@Param("id")Long id, @Param("status")StatusEnum status);
}
