package com.app.toko.service;

import java.util.List;
import java.util.UUID;

import com.app.toko.payload.request.CreateContactRequest;
import com.app.toko.payload.request.UpdateContactRequest;
import com.app.toko.payload.response.ContactResponse;

public interface ContactService {
    public List<ContactResponse> getAllContactsByUserId(UUID userId);

    public ContactResponse getContact(UUID contactId);

    public ContactResponse createContact(UUID userId, CreateContactRequest createContactRequest);

    public void updateContact(UUID contactId, UpdateContactRequest updateContactRequest);

    public void deleteContact(UUID contactId);
}
