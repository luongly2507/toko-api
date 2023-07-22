package com.app.toko.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.toko.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, UUID> {

    List<Contact> findByUserId(UUID userId);

}
