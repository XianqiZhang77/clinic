package com.soen6841.demo.controller;

import com.soen6841.demo.domain.User;
import com.soen6841.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    /*
     * 
     */
    @RequestMapping("/create_account_submit")
    public String signUpSubmit(User user, Model model) {
    	if(userService.createAccount(user)) {
    		return "create_success";
    	}
        model.addAttribute("msg", "This User ID already exsit.");
        return "create_account";
    }
    
    /*
     * 
     */
    @RequestMapping("/login_submit")
    public String loginSubmit(User user, Model model) {
    	if(userService.checkExsit(user)) {
	    	if(userService.login(user)) {
	    		return userService.getJumpPage(user);
	    	}
	    	model.addAttribute("msg", "Incorrect ID or password. Please try again.");
	    	return "index";
    	}
    	model.addAttribute("msg", "This User ID not exsit. Please try again.");
    	return "index";
    }
    
    
}