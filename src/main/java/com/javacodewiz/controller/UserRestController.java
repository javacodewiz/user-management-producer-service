package com.javacodewiz.controller;


import com.javacodewiz.dto.UserDto;
import com.javacodewiz.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody  UserDto dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
    }

    @GetMapping("/{id}")
    @Cacheable(cacheNames = "user",key = "#id")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id)
    {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    @Cacheable(cacheNames = "user",key = "#ids")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping
    @CachePut(cacheNames = "user",key = "#dto.id")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto dto)
    {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "user",key = "#id")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id)
    {
        return  ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }
}
