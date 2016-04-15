package com.g4s.javelin.data.repository.location;

import com.g4s.javelin.data.model.location.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jalonzo on 4/14/16.
 */
public interface ContactRepository extends JpaRepository<ContactModel, Long> {

    List<ContactModel> findByContactAccountNumber(final String accountNumber);

}
