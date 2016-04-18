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
    private List<ContactDTO> contactDTOList;

    @InjectMocks
    private ContactApi contactApi = new ContactApi();

    @Test
    public void testSaveContact() throws Exception {
        contactApi.saveContact(contactDTO);
        Mockito.verify(contactService, Mockito.times(1)).saveContact(Mockito.any(ContactDTO.class));
    }

    @Test
    public void testGetContactByAccountNumber() throws Exception {
        Mockito.when(contactService.getContactByAccountNumber(Mockito.anyString())).thenReturn(contactDTOList);
        assertNotNull(contactService.getContactByAccountNumber("001122"));
    }
}
