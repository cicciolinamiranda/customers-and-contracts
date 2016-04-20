package com.g4s.javelin.api;

import com.g4s.javelin.dto.core.location.ContactDTO;
import com.g4s.javelin.service.location.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Created by jalonzo on 4/16/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactApiTest {

    @Mock
    ContactService contactService;

    @Mock
    ContactDTO contactDTO;

    @Mock
    List<ContactDTO> contactDTOList;

    @InjectMocks
    private ContactApi contactApi = new ContactApi();

    @Test
    public void testSaveContact() throws Exception {
        contactApi.saveContact(contactDTO);
        Mockito.verify(contactService, Mockito.times(1)).saveContact(Mockito.any(ContactDTO.class));
    }

    @Test
    public void testGetContactByAccountNumber() throws Exception {
        Mockito.when(contactService.getContactByAccountNumber("001122")).thenReturn(contactDTOList);
        assertSame(contactDTOList, contactApi.getContactByAccountNumber("001122"));
        assertNotNull(contactService.getContactByAccountNumber("001122"));
    }
}
