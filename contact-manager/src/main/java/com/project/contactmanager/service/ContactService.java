package com.project.contactmanager.service;

import com.project.contactmanager.dto.ContactDto;
import com.project.contactmanager.model.Contact;
import com.project.contactmanager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface ContactService {

    public Contact addNewContact(ContactDto contactDto, User user) throws IOException;

    public Page<Contact> findAllByUserId(Long userId, Pageable pageable);

    public void deleteContactById(Long contactId);

    public Contact updateContactById(Long contactId, ContactDto contactDto, User user) throws IOException;

    public Contact findById(Long contactId);
}
