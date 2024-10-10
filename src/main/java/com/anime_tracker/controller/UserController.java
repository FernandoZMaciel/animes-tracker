package com.anime_tracker.controller;

import com.anime_tracker.model.User;
import com.anime_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody User requestUser) {
        return userService.updateUser(requestUser);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteUserById(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}
