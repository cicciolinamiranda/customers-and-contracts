package com.g4s.javelin.service.location.impl;

import com.g4s.javelin.data.model.location.ContactModel;
import com.g4s.javelin.data.model.location.CustomerModel;
import com.g4s.javelin.data.repository.location.ContactRepository;
import com.g4s.javelin.dto.core.location.ContactDTO;
import com.g4s.javelin.service.location.ContactService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

/**
 * Created by jalonzo on 4/20/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest extends TestCase {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService = new ContactServiceImpl();

    ContactDTO contactDTO;
    ContactModel contactModel;

    List<ContactModel> contactModelList = Lists.newArrayList();

    @Before
    public void setUp() throws Exception {
        setupContactDTO();
        setupContactModel();
    }

    private void setupContactModel() {
        contactModel = new ContactModel();
        contactModel.setId(1L);
        contactModel.setContactAccountNumber("999999");
        contactModel.setSalutation("Don");
        contactModel.setMiddleName("Andolini");
        contactModel.setFirstName("Vito");
        contactModel.setLastName("Corleone");
        contactModel.setJobTitle("Godfather");
        contactModel.setEmail("vcorleone@godfather.com");
        contactModel.setFax("000-992-101");
        contactModel.setMobile("1234567890");
        contactModel.setPhone1("123456");
        contactModel.setPhone2("678901");
        contactModel.setInactive(false);

        CustomerModel customer = new CustomerModel();
        customer.setCustomerName("Juan Dela Cruz");
        customer.setId(9L);
        customer.setCustomerNumber("111111");
        customer.setCustomerName("Juan Dela Cruz");
        customer.setCustomerCode("222222");
        customer.setDunsNumber("333333");
        customer.setAccountNumber("444444");
        customer.setAccountDescription("Description");
        customer.setManualCustomerCode("454545");
        customer.setVatNumber("908070");
        customer.setAddressLine1("221B Baker Street");
        customer.setCity("Westminster");
        customer.setTbc("tbc");
        contactModel.setCustomer(customer);
    }

    private void setupContactDTO() {
        contactDTO = new ContactDTO();
        contactDTO.setId(9L);
        contactDTO.setContactAccountNumber("999999");
        contactDTO.setSalutation("Don");
        contactDTO.setMiddleName("Andolini");
        contactDTO.setFirstName("Vito");
        contactDTO.setLastName("Corleone");
        contactDTO.setJobTitle("Godfather");
        contactDTO.setEmail("vcorleone@godfather.com");
        contactDTO.setFax("000-992-101");
        contactDTO.setMobile("1234567890");
        contactDTO.setPhone1("123456");
        contactDTO.setPhone2("678901");
        contactDTO.setInactive(false);

        CustomerModel customer = new CustomerModel();
        customer.setId(9L);
        customer.setCustomerNumber("111111");
        customer.setCustomerName("Juan Dela Cruz");
        customer.setCustomerCode("222222");
        customer.setDunsNumber("333333");
        customer.setAccountNumber("444444");
        customer.setAccountDescription("Description");
        customer.setManualCustomerCode("454545");
        customer.setVatNumber("908070");
        customer.setAddressLine1("221B Baker Street");
        customer.setCity("Westminster");
        customer.setTbc("tbc");
        contactDTO.setCustomer(customer);

    }

    @Test
    public void testSaveContact() throws Exception {
        Mockito.when(contactRepository.save(contactModel)).thenReturn(contactModel);
        ContactDTO dto = contactService.saveContact(contactDTO);

        Mockito.verify(contactRepository, Mockito.times(1)).save(Mockito.any(ContactModel.class));
        assertEquals("Don", dto.getSalutation());
        assertEquals("Vito", dto.getFirstName());
        assertEquals("Corleone", dto.getLastName());
        assertEquals(9L, dto.getId().longValue());
    }


    @Test
    public void testGetContactByAccountNumber() throws Exception {
        contactModelList.add(contactModel);
        System.out.println(contactModelList.get(0));

        Mockito.when(contactRepository.findByContactAccountNumber("999999")).thenReturn(contactModelList);

        List<ContactDTO> contactDTOList = contactService.getContactByAccountNumber("999999");
        
        Mockito.verify(contactRepository, Mockito.times(1)).findByContactAccountNumber("999999");
        assertTrue(!contactDTOList.isEmpty());
        assertEquals(1, contactDTOList.size());
        assertEquals("Don", contactDTOList.get(0).getSalutation());
        assertEquals("Vito",  contactDTOList.get(0).getFirstName());
        assertEquals("Corleone",  contactDTOList.get(0).getLastName());

    }
}