package com.javacodewiz.service;

import com.javacodewiz.dto.UserDto;

import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto dto);

    public UserDto getUserById(long id);

    public List<UserDto> getAllUsers();


    public UserDto updateUser(UserDto dto);

    public String deleteUser(long id);
}
