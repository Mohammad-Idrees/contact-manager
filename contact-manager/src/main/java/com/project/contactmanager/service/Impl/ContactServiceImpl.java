package com.project.contactmanager.service.Impl;

import com.project.contactmanager.dto.ContactDto;
import com.project.contactmanager.model.Contact;
import com.project.contactmanager.model.User;
import com.project.contactmanager.repository.ContactRepository;
import com.project.contactmanager.service.ContactService;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    private static String UPLOAD_FOLDER = "src/main/resources/static/contacts/";

    @Override
    public Contact addNewContact(ContactDto contactDto, User user) throws IOException {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactDto, contact, "contactId");
        MultipartFile file = contactDto.getFile();
        if(!file.isEmpty()){
            // logic for uploading files to our local storage
            addImageUtil(file, contact);
        }else{
            contact.setImage("default.png");
        }
        contact.setUser(user);
        Contact savedContact = contactRepository.save(contact);
        return savedContact;
    }

    @Override
    public Page<Contact> findAllByUserId(Long userId, Pageable pageable) {
        return contactRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public void deleteContactById(Long contactId) {
        deleteImageUtil(contactId);
        contactRepository.deleteById(contactId);
        return;
    }

    @Override
    public Contact findById(Long contactId) {
        return contactRepository.findById(contactId).get();
    }

    @Override
    public Contact updateContactById(Long contactId, ContactDto contactDto, User user) throws IOException {
        Contact contact = contactRepository.findById(contactId).get();
        BeanUtils.copyProperties(contactDto, contact);
        MultipartFile file = contactDto.getFile();
        if(!file.isEmpty()){
            // logic for uploading files to our local storage
            deleteImageUtil(contactId);
            addImageUtil(file, contact);
        }
        contact.setUser(user);
        Contact updatedContact = contactRepository.save(contact);
        return updatedContact;
    }

    private void addImageUtil(MultipartFile file, Contact contact) throws IOException {
        // logic for uploading files to our local storage
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_FOLDER  + contact.getPhone() + file.getOriginalFilename());
        Files.write(path, bytes);
        contact.setImage(file.getOriginalFilename());
        return;
    }

    private void deleteImageUtil(Long contactId){
        try {
            Contact contact = contactRepository.findById(contactId).get();
            if(!contact.getImage().equals("default.png")) {
                File file = new File(UPLOAD_FOLDER  + contact.getPhone() + contact.getImage());
                if (file.delete()) {
                    System.out.println(file.getName() + " is deleted!");
                } else {
                    System.out.println("Delete operation is failed.");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Failed to Delete image !!");
        }
    }
}
