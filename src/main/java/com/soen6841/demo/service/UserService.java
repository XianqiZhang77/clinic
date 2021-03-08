package com.soen6841.demo.service;

import com.soen6841.demo.dao.DoctorRepository;
import com.soen6841.demo.dao.NurseRepository;
import com.soen6841.demo.dao.PatientRepository;
import com.soen6841.demo.dao.UserRepository;
import com.soen6841.demo.domain.Doctor;
import com.soen6841.demo.domain.Nurse;
import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.User;
import com.soen6841.demo.util.PasswordUtil;
import com.soen6841.demo.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    NurseRepository nurseRepository;
    
    @Autowired
    DoctorRepository doctorRepository;
    
    @Autowired
    PatientRepository patientRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public boolean checkExsit(User user) {
    	if(userRepository.existsByUserID(user.getUserID())) {
    		return true;
    	}
    	return false;
    }
    
    public User getUserByUserID(String userID) {
    	return userRepository.findOneByUserID(userID);
    }
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean createAccount(User user) {
    	if(userRepository.existsByUserID(user.getUserID())) {
    		return false;
    	}
    	user.setPassword(PasswordUtil.encrypt(user.getPassword()));
    	userRepository.save(user);
        return true;
    }
    
    public boolean login(User user) {
    	if(userRepository.existsByUserID(user.getUserID())) {
    		User login = userRepository.findOneByUserID(user.getUserID());
    		String md5Password = PasswordUtil.encrypt(user.getPassword());
    		if(login.getPassword().equals(md5Password)) {
    			return true;
    		}
    	}
        return false;
    }
    
    public String getJumpPage(User user, Model model) {
    	User check = userRepository.findOneByUserID(user.getUserID());
    	String type = check.getUserType();
    	if(type == null) {
    		return "type";
    	}
    	
    	switch(check.getUserType()) {
    	  case "manager":
    		  return "manager_success";
    		  
    	  case "doctor":	  
    		  return doctorHandler(check, model);
    		  
    	  case "nurse":
    		  return nurseHandler(check, model);
    		  
    	  case "patient":
    		  return patientHandler(check, model);
    		  
    	  default:
    		  return "type";
    	}
    }
    
    public String doctorHandler(User user, Model model) {
    	Doctor doctor = doctorRepository.findOneById(user.getRegisterID());
    	Status status = doctor.getRegisterStatus();
    	if(status == Status.accepted) {
    		return "doctor";
    	}else if(status == Status.wating) {
    		model.addAttribute("msg", "Your register is pending, please wait.");
    		return "index";
    	}else if(status == Status.rejected) {
    		user.setUserType(null);
    		user.setRegisterID(null);
    		userRepository.save(user);
    		model.addAttribute("msg", "Your register was rejected, please register again.");
    		return "type";
    	}
    	return "type";
    }
    
    public String nurseHandler(User user, Model model) {
    	Nurse nurse = nurseRepository.findOneById(user.getRegisterID());
    	Status status = nurse.getRegisterStatus();
    	if(status == Status.accepted) {
    		return "nurse_success";
    	}else if(status == Status.wating) {
    		model.addAttribute("msg", "Your register is pending, please wait.");
    		return "index";
    	}else if(status == Status.rejected) {
    		user.setUserType(null);
    		user.setRegisterID(null);
    		userRepository.save(user);
    		model.addAttribute("msg", "Your register was rejected, please register again.");
    		return "type";
    	}
    	return "type";
    }  
    
    public String patientHandler(User user, Model model) {
    	Patient patient = patientRepository.findOneById(user.getRegisterID());
    	Status status = patient.getRegisterStatus();
    	if(status == Status.accepted) {
    		return "enter_assessment";
    	}else if(status == Status.wating) {
    		model.addAttribute("msg", "Your register is pending, please wait.");
    		return "index";
    	}else if(status == Status.rejected) {
    		user.setUserType(null);
    		user.setRegisterID(null);
    		userRepository.save(user);
    		model.addAttribute("msg", "Your register was rejected, please register again.");
    		return "type";
    	}
    	return "type";
    }  
    
}
