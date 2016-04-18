package com.g4s.javelin.service.location.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.data.model.location.ContactModel;
import com.g4s.javelin.data.repository.location.ContactRepository;
import com.g4s.javelin.dto.core.location.ContactDTO;
import com.g4s.javelin.service.location.ContactService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

/**
 * Created by jalonzo on 4/14/16.
 */
public class ContactServiceImpl implements ContactService {

    @Autowired
    @Lazy
    private ContactRepository contactRepository;

    private ModelMapper modelMapper;

    public ContactServiceImpl() {
        modelMapper = new ModelMapper();
    }

    @Override
    public ContactDTO saveContact(final ContactDTO contactDTO) {

        ContactModel model = modelMapper.map(contactDTO, ContactModel.class);
        if (contactDTO.getId() != null) {
            model.setId(contactDTO.getId());
        }
        contactRepository.save(model);

        return transformContact(model);
    }

    @Override
    public List<ContactDTO> getContactByAccountNumber(final String accountNumber) {
        List<ContactModel> results = contactRepository.findByContactAccountNumber(accountNumber);
        List<ContactDTO> list = Lists.newArrayList();
        for (ContactModel result : results) {
            list.add(transformContact(result));
        }
        return list;
    }

    private ContactDTO transformContact(final ContactModel contactModel) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO = modelMapper.map(contactModel, ContactDTO.class);

        return contactDTO;
    }
}
