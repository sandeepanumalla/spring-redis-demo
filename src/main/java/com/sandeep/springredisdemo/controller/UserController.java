package com.sandeep.springredisdemo.controller;

import com.sandeep.springredisdemo.model.User;
import com.sandeep.springredisdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {


    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        boolean result = userService.saveUser(user);
        if(result) {
            return ResponseEntity.ok("User Created Successfully!!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> fetchAllUser() {
        List<User> userList = userService.fetchAllUsers();
        return ResponseEntity.ok(userList);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
