package com.g4s.javelin.service.location;

import com.g4s.javelin.dto.core.location.ContactDTO;

import java.util.List;
/**
 * Created by jalonzo on 4/14/16.
 */
public interface ContactService {
    ContactDTO saveContact(ContactDTO contactDTO);
    List<ContactDTO> getContactByAccountNumber(final String accountNumber);
}
