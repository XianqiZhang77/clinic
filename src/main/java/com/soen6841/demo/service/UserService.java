package com.soen6841.demo.service;

import com.soen6841.demo.dao.UserRepository;
import com.soen6841.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public boolean checkExsit(User user) {
    	if(userRepository.existsByUserID(user.getUserID())) {
    		return true;
    	}
    	return false;
    }

    public boolean createAccount(User user) {
    	if(userRepository.existsByUserID(user.getUserID())) {
    		return false;
    	}
    	userRepository.save(user);
        return true;
    }
    
    public boolean login(User user) {
    	if(userRepository.existsByUserID(user.getUserID())) {
    		User login = userRepository.findOneByUserID(user.getUserID());
    		if(login.getPassword().equals(user.getPassword())) {
    			return true;
    		}
    	}
        return false;
    }
    
    public String getJumpPage(User user) {
    	User check = userRepository.findOneByUserID(user.getUserID());
    	
    	switch(check.getRegisterStatus()) {
    		case 0:
    			return "type";
    		case 1:
    			return "type";
    		case 2:
    			break;
    		case 3:
    			break;
    		default:
    			return "type";
    	}
    	
    	switch(check.getUserType()) {
    	  case "manager":
    		  return "manager";
    	  case "doctor":
    		  return "doctor";
    	  case "nurse":
    		  return "nurse";
    	  case "patient":
    		  return "patient";
    	  default:
    		  return "type";
    	}
    }   
    
}
