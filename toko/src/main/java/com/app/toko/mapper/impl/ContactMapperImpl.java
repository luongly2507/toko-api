package com.app.toko.mapper.impl;

import org.springframework.stereotype.Component;

import com.app.toko.entity.Contact;
import com.app.toko.mapper.ContactMapper;
import com.app.toko.payload.request.CreateContactRequest;
import com.app.toko.payload.request.UpdateContactRequest;
import com.app.toko.payload.response.ContactResponse;

@Component
public class ContactMapperImpl implements ContactMapper {

    @Override
    public ContactResponse toContactResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .telephone(contact.getTelephone())
                .receiver(contact.getReceiver())
                .city(contact.getCity())
                .district(contact.getDistrict())
                .ward(contact.getWard())
                .line(contact.getLine())
                .isDefault(contact.getIsDefault())
                .build();
    }

    @Override
    public Contact toContact(CreateContactRequest createContactRequest) {
        return Contact.builder()
                .receiver(createContactRequest.getReceiver())
                .line(createContactRequest.getLine())
                .ward(createContactRequest.getWard())
                .district(createContactRequest.getDistrict())
                .city(createContactRequest.getCity())
                .telephone(createContactRequest.getTelephone())
                .build();
    }

    @Override
    public void updateContact(UpdateContactRequest updateContactRequest, Contact contact) {
        contact.setCity(updateContactRequest.getCity());
        contact.setDistrict(updateContactRequest.getDistrict());
        contact.setWard(updateContactRequest.getWard());
        contact.setLine(updateContactRequest.getLine());
        contact.setTelephone(updateContactRequest.getTelephone());
        contact.setReceiver(updateContactRequest.getReceiver());
        contact.setIsDefault(updateContactRequest.getIsDefault());
    }

}
