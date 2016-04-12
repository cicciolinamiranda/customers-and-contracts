package com.g4s.javelin.data.repository.location;

import com.g4s.javelin.data.model.location.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    List<CustomerModel> getAllCustomers();

    List<CustomerModel> findByCustomerName(final String customerName);


//    @Query("SELECT CL FROM CustomerModel CL where CL.id <> ?1 AND"
//            + " (CL.id = ?2 OR CL.customer.customerName LIKE %?3% OR CL.address.address LIKE %?4%)")
    List<CustomerModel> findBySearchTerm(Long id, String customerName);

}
