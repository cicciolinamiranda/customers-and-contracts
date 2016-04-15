package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.location.ContactDTO;
/**
 * Created by jalonzo on 4/14/16.
 */
public interface ContactService {
    ContactDTO saveContact(ContactDTO contactDTO);
    List<ContactDTO> getContactByAccountNumber(final String accountName);
}
