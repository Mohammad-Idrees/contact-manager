package com.project.contactmanager.service.Impl;

import com.project.contactmanager.dto.UserDto;
import com.project.contactmanager.model.Contact;
import com.project.contactmanager.model.User;
import com.project.contactmanager.repository.UserRepository;
import com.project.contactmanager.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private static String UPLOAD_FOLDER = "src/main/resources/static/users-img/";

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User registerNewUserAccount(UserDto userDto) throws Exception {
        if(emailExists(userDto.getEmail())){
            throw new Exception("Account registered with email " + userDto.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("USER");
        user.setActive(true);
        user.setImage("default.png");
        return userRepository.save(user);
    }

    @Override
    public User updateUserProfile(UserDto userDto, User user) throws IOException {
        BeanUtils.copyProperties(userDto, user, "password");
        MultipartFile file = userDto.getFile();
        if(!file.isEmpty()){
            deleteImageUtil(user);
            addImageUtil(file, user);
        }
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    private void addImageUtil(MultipartFile file, User user) throws IOException {
        // logic for uploading files to our local storage
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_FOLDER  + user.getUserId() + file.getOriginalFilename());
        Files.write(path, bytes);
        user.setImage(file.getOriginalFilename());
        return;
    }

    private void deleteImageUtil(User user){
        try {
            if(!user.getImage().equals("default.png")) {
                File file = new File(UPLOAD_FOLDER  + user.getUserId() + user.getImage());
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


    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
