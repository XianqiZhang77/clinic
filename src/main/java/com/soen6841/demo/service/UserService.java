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

    public User createUser(User user) {
    	if(userRepository.existsByUserName(user.getUserName())) {
    		return new User();
    	}
        return userRepository.save(user);
    }
    
    public User login(User user) {
    	if(userRepository.existsByUserName(user.getUserName())) {
    		User get = userRepository.findOneByUserName(user.getUserName());
    		if(get.getPassword().equals(user.getPassword())) {
    			return get;
    		}
    	}
        return new User();
    }
    
    
}
