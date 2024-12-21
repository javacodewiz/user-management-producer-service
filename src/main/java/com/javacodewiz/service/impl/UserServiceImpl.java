package com.javacodewiz.service.impl;

import com.javacodewiz.dto.UserDto;
import com.javacodewiz.exception.ResourceNotFoundException;
import com.javacodewiz.mapper.UserMapper;
import com.javacodewiz.model.User;
import com.javacodewiz.repository.UserRepository;
import com.javacodewiz.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDto createUser(UserDto dto) {
        log.info("Inside create user");
        User user = UserMapper.mapToUser(dto);
        log.info("User retrive from User Dto");
        user = repository.save(user);
        log.info("User saved in DB");
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto getUserById(long id) {
        log.info("Inside get user by id");
        Optional<User> user = repository.findById(id);
        if(user.isPresent()){
            log.info("User retrive from DB");
            return UserMapper.mapToUserDto(user.get());
        }else {
            log.warn("User not found with id "+id);
            throw new ResourceNotFoundException("User not found with id "+id);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Inside get all users");
        return repository.findAll().stream().map(UserMapper::mapToUserDto).toList();
    }

    @Override
    public UserDto updateUser(UserDto dto) {
        log.info("Inside update user");
        Optional<User> user = repository.findById(dto.getId());
        if(user.isPresent()){
            User user1 = user.get();
            user1.setFirstName(dto.getFirstName());
            user1.setLastName(dto.getLastName());
            user1.setEmail(dto.getEmail());
            user1.setPhoneNumber(dto.getPhoneNumber());
            user1.setAddress(dto.getAddress());
            user1 = repository.save(user1);
            log.info("User updated in DB");
            return UserMapper.mapToUserDto(user1);
        }else {
            log.warn("User not found with id "+dto.getId());
            throw new ResourceNotFoundException("User not found with id "+dto.getId());
        }
    }

    @Override
    public String deleteUser(long id) {
        log.info("Inside delete user");
        repository.deleteById(id);
        log.info("User deleted with id "+id);
        return "User deleted with id "+id;
    }
}
