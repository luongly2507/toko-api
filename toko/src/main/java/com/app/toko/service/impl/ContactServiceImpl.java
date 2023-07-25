package com.app.toko.service.impl;

import java.util.List;
import java.util.UUID;

import com.app.toko.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.toko.entity.Contact;
import com.app.toko.mapper.ContactMapper;
import com.app.toko.payload.request.CreateContactRequest;
import com.app.toko.payload.request.UpdateContactRequest;
import com.app.toko.payload.response.ContactResponse;
import com.app.toko.repository.ContactRepository;
import com.app.toko.repository.UserRepository;
import com.app.toko.service.ContactService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactMapper contactMapper;

    @Override
    public List<ContactResponse> getAllContactsByUserId(UUID userId) {
        return contactRepository.findByUserId(userId).stream().map(contact -> contactMapper.toContactResponse(contact))
                .toList();
    }

    @Override
    public ContactResponse getContact(UUID contactId) {
        return contactMapper.toContactResponse(contactRepository.findById(
                contactId).orElseThrow());
    }

    @Override
    public ContactResponse createContact(UUID userId, CreateContactRequest createContactRequest) {
        Contact contact = contactMapper.toContact(createContactRequest);
        User user = userRepository.findById(userId).orElseThrow();
        if(user.getContacts() != null && user.getContacts().size() == 0)
        {
            contact.setIsDefault(true);
        }
        else contact.setIsDefault(false);
        contact.setUser(user);
        return contactMapper.toContactResponse(contactRepository.save(contact));
    }

    @Override
    public void updateContact(UUID contactId, UpdateContactRequest updateContactRequest) {
        Contact contact = contactRepository.findById(contactId).orElseThrow();
        contactMapper.updateContact(updateContactRequest, contact);
        contactRepository.save(contact);
    }

    @Override
    public void deleteContact(UUID contactId) {
        contactRepository.deleteById(contactId);
    }

}
