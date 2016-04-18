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


//    private ContactModel transformContactDTOtoModel(final ContactDTO contactDTO) {
//        ContactModel contact = new ContactModel();
//        if (contactDTO != null) {
//            contact.setContactAccountNumber(contactDTO.getContactAccountNumber());
//            contact.setSalutation(contactDTO.getSalutation());
//            contact.setFirstName(contactDTO.getFirstName());
//            contact.setMiddleName(contactDTO.getMiddleName());
//            contact.setLastName(contactDTO.getLastName());
//            contact.setJobTitle(contactDTO.getJobTitle());
//            contact.setPhone1(contactDTO.getPhone1());
//            contact.setPhone2(contactDTO.getPhone2());
//            contact.setMobile(contactDTO.getMobile());
//            contact.setEmail(contactDTO.getEmail());
//            contact.setFax(contactDTO.getFax());
//            contact.setPrimaryContact(contactDTO.getPrimaryContact());
//            contact.setInactive(contactDTO.isInactive());
//        }
//        return contact;
//    }
//
//    private ContactDTO transformContactModeltoDTO(final ContactModel contractModel) {
//        ContactDTO contactDTO = new ContactDTO();
//        if (contractModel != null) {
//            contactDTO.setContactAccountNumber(contractModel.getContactAccountNumber());
//            contactDTO.setSalutation(contractModel.getSalutation());
//            contactDTO.setFirstName(contractModel.getFirstName());
//            contactDTO.setMiddleName(contractModel.getMiddleName());
//            contactDTO.setLastName(contractModel.getLastName());
//            contactDTO.setJobTitle(contractModel.getJobTitle());
//            contactDTO.setPhone1(contractModel.getPhone1());
//            contactDTO.setPhone2(contractModel.getPhone2());
//            contactDTO.setMobile(contractModel.getMobile());
//            contactDTO.setEmail(contractModel.getEmail());
//            contactDTO.setFax(contractModel.getFax());
//            contactDTO.setPrimaryContact(contractModel.getPrimaryContact());
//            contactDTO.setInactive(contractModel.isInactive());
//        }
//        return contactDTO;
//    }
//
//    private List<ContactDTO> transformContactModelListToDTO(final List<ContactModel> contactModelList) {
//        List<ContactDTO> contactDTOList = new ArrayList<>();
//        for (ContactModel contactModel : contactModelList) {
//            if (contactModel != null) {
//                contactDTOList.add(transformContactModeltoDTO(contactModel));
//            }
//        }
//        return contactDTOList;
//    }
//
