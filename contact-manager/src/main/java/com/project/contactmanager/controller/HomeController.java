package com.project.contactmanager.controller;

import com.project.contactmanager.dto.UserDto;
import com.project.contactmanager.model.User;
import com.project.contactmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    private String home(){
        return "home";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(ModelMap model){
        UserDto userDto = new UserDto();
        model.put("userDto", userDto);
        return "registration";
    }

    /*
    Validations that the controller will perform when registering a new account:
    1. All required fields are filled (No empty or null fields)
    2. The email address is valid (well-formed)
    3. The password confirmation field matches the password field
    4. The account doesn't already exist
    */

    @PostMapping("/registration")
    public String registerUserAccount(@Valid @ModelAttribute("userDto") UserDto userDto,
                                      BindingResult result,
                                      ModelMap model) throws Exception {

        try{
            if(result.hasErrors()){
                return "registration";
            }
            User registered = userService.registerNewUserAccount(userDto);
        }catch (Exception e){
            model.put("message", e.getMessage());
            return "registration";
        }
        model.put("message", "Registration Successful");
        model.put("userDto", new UserDto());
        return "registration";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

}
