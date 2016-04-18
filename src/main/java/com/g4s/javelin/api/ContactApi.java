package com.g4s.javelin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.location.ContactDTO;
import com.g4s.javelin.service.location.ContactService;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

/**
 * Created by jalonzo on 4/14/16.
 */
@Api(
        name = ApiConstants.API_NAME,
        version = ApiConstants.API_VERSION,
        namespace = @ApiNamespace(ownerDomain = ApiConstants.API_NAMESPACE_OWNER_DOMAIN,
        ownerName = ApiConstants.API_NAMESPACE_OWNER_NAME),
        description = ApiConstants.API_DESCRIPTION)
public class ContactApi {

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.CONTACT_SERVICE)
    private ContactService contactService;

    /**
     * Add new contact
     *
     * @param contactDTO
     */
    @ApiMethod(
            name = "contact.add",
            path = "contact",
            httpMethod = ApiMethod.HttpMethod.POST)
    public void saveContact(final ContactDTO contactDTO) {
        contactService.saveContact(contactDTO);
    }

    @ApiMethod(
            name = "contact.getByAccountNumber",
            path = "contact/getByAccountNumber",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<ContactDTO> getContactByAccountNumber(@Named("accountNumber") final String accountNumber) {
        return contactService.getContactByAccountNumber(accountNumber);
    }
}
