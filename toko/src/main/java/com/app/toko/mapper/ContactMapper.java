package com.app.toko.mapper;

import com.app.toko.entity.Contact;
import com.app.toko.payload.request.CreateContactRequest;
import com.app.toko.payload.request.UpdateContactRequest;
import com.app.toko.payload.response.ContactResponse;

public interface ContactMapper {
    public ContactResponse toContactResponse(Contact contact);

    public Contact toContact(CreateContactRequest createContactRequest);

    public void updateContact(UpdateContactRequest createContactRequest, Contact contact);
}
