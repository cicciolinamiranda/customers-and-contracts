package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.location.ContactModel;

/**
 * Created by jalonzo on 4/14/16.
 */
public interface ContactRepository extends JpaRepository<ContactModel, Long> {

    List<ContactModel> findByContactAccountNumber(final String accountNumber);

}
