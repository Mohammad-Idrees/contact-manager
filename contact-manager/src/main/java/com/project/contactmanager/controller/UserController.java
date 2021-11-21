package com.project.contactmanager.controller;

import com.project.contactmanager.dto.ContactDto;
import com.project.contactmanager.dto.UserDto;
import com.project.contactmanager.model.Contact;
import com.project.contactmanager.model.User;
import com.project.contactmanager.service.ContactService;
import com.project.contactmanager.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute
    public void getPrincipalDetails(ModelMap model, Principal principal){
        User user =  userService.findByEmail(principal.getName());
        model.put("user", user);
    }

    @GetMapping("/dashboard")
    public String hello(){
        return "users/dashboard";
    }

    @GetMapping("/add-contact")
    public String showContactForm(ModelMap model){
        ContactDto contactDto = new ContactDto();
        model.put("contactDto", contactDto);
        return "users/contact-form";
    }

    @PostMapping("/contacts")
    public String addContact(@Valid @ModelAttribute("contactDto") ContactDto contactDto,
                             BindingResult result,
                             ModelMap model) throws IOException {
        if(result.hasErrors()){
            return "users/contact-form";
        }
        contactService.addNewContact(contactDto, (User) model.getAttribute("user"));
        model.put("message", "Contact added successfully");
        model.put("contactDto", new ContactDto());
        return "users/contact-form";
    }

    @GetMapping("/contacts")
    public String showContacts(@RequestParam(name="page", required = false, defaultValue = "1") String page, ModelMap model){
        int recordsPerPage = 5;
        Pageable pageable = PageRequest.of(Integer.parseInt(page)-1, recordsPerPage);
        User user = (User) model.getAttribute("user");
        Page<Contact> contacts = contactService.findAllByUserId(user.getUserId(), pageable);
        model.put("contacts", contacts.getContent());
        model.put("currentPage", page);
        model.put("noOfPages", contacts.getTotalPages());
        return "users/contacts";
    }

    @GetMapping("/contacts/{id}")
    public String viewContact(@PathVariable("id") Long contactId,
                              ModelMap model) throws Exception {
        Contact contact = contactService.findById(contactId);
        User user = (User) model.getAttribute("user");
        if(contact.getUser().getUserId() != user.getUserId()){
            throw new Exception("Contact Not Found");
        }
        ContactDto contactDto = new ContactDto();
        BeanUtils.copyProperties(contact, contactDto);
        model.put("contactDto", contactDto);
        return "users/viewContact";
    }

    @GetMapping("/edit-contact/{id}")
    public String showEditContactForm(@PathVariable("id") Long contactId,
                                      ModelMap model) throws Exception {
        Contact contact = contactService.findById(contactId);
        User user = (User) model.getAttribute("user");
        if(contact.getUser().getUserId() != user.getUserId()){
            throw new Exception("Invalid request");
        }
        ContactDto contactDto = new ContactDto();
        BeanUtils.copyProperties(contact, contactDto);
        model.put("contactDto", contactDto);
        return "users/editForm";
    }

    @PostMapping("/edit-contact/{id}")
    public String editContact(@Valid @ModelAttribute ContactDto contactDto,
                              BindingResult result,
                              @PathVariable("id") Long id,
                              ModelMap model,
                              HttpSession session) throws IOException {
        if(result.hasErrors()){
            return "users/editForm";
        }

        contactService.updateContactById(id,
                contactDto,
                (User) model.getAttribute("user")
        );
        return "redirect:/users/contacts";
    }

    @GetMapping("/delete-contact/{id}")
    //@DeleteMapping("/delete-contact/{id}")
    public String deleteContact(@PathVariable("id") Long contactId,
                                ModelMap model) throws Exception {
        Contact contact = contactService.findById(contactId);
        User user = (User) model.getAttribute("user");
        if(contact.getUser().getUserId() != user.getUserId()) {
            throw new Exception("Invalid request");
        }
        contactService.deleteContactById(contactId);
        return "redirect:/users/contacts";
    }


    @GetMapping("/profile")
    public String showUserProfile(ModelMap model){
        return "users/user-profile";
    }

    @GetMapping("/edit-profile")
    public String editUserProfile(ModelMap model){
        User user = (User) model.getAttribute("user");
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        model.put("userDto", userDto);
        return "users/edit-profile";
    }

    @PostMapping("/edit-profile")
    public String updateUserProfile(@Valid @ModelAttribute UserDto userDto, ModelMap model) throws IOException {
        User user = (User) model.getAttribute("user");
        userService.updateUserProfile(userDto, user);
        model.put("message", "User Profile Updated");
        return "users/user-profile";
    }

    @GetMapping("/settings")
    public String showSettingForm(){
        return "users/settings";
    }

    @PostMapping("/change-password")
    public String updatePassword(@RequestParam("old-password") String oldPassword,
                                 @RequestParam("new-password") String newPassword,
                                 ModelMap model){
        User user = (User) model.getAttribute("user");
        if(!passwordEncoder.matches(oldPassword, user.getPassword())){
            model.put("message", "Incorrect Password");
            return "users/settings";
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userService.saveUser(user);
        model.put("message", "Password Updated");
        return "users/settings";
    }



}