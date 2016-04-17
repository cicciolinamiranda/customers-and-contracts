package com.g4s.javelin.api;

import com.g4s.javelin.dto.core.location.ContactDTO;
import com.g4s.javelin.service.location.ContactService;
import com.g4s.javelin.service.location.impl.ContactServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jalonzo on 4/16/16.
 */
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

 //       contactApi.saveContact(contactDTO);
//        Mockito.

//        barredEmployeeApi.saveBarredEmployees(barredEmployeesList);
//        Mockito.verify(barredEmployeeService, Mockito.times(1)).saveBarredEmployees(Mockito.anyListOf(
//                BarredEmployeeDTO.class), Mockito.anyLong());

    }

    @Test
    public void testGetContactByAccountNumber() throws Exception {

    }
}