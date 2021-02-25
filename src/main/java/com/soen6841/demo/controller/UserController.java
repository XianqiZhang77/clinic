package com.soen6841.demo.controller;

import com.soen6841.demo.domain.User;
import com.soen6841.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        User user_1 = userService.createUser(user);
        return new ResponseEntity<User>(user_1, HttpStatus.CREATED);
    }
    
    @RequestMapping("/login")
    public String login(User user) {
    	if(userService.login(user)) {
    		return "manager";
    	}
    	return "index";
    }
}