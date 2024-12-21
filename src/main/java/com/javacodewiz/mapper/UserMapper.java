package com.javacodewiz.mapper;

import com.javacodewiz.dto.UserDto;
import com.javacodewiz.model.User;

public class UserMapper {

    public static User mapToUser(UserDto dto)
    {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(dto.getAddress());
        return user;
    }


    public static UserDto mapToUserDto(User dto)
    {
        UserDto user = new UserDto();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(dto.getAddress());
        return user;
    }
}
