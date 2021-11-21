package com.project.contactmanager.service;

import com.project.contactmanager.dto.UserDto;
import com.project.contactmanager.model.User;

import java.io.IOException;

public interface UserService {

    User registerNewUserAccount(UserDto userDto) throws Exception;

    User findByEmail(String email);

    User updateUserProfile(UserDto userDto, User user) throws IOException;

    User saveUser(User user);

}
