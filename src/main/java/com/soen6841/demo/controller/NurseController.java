package com.soen6841.demo.controller;

import com.soen6841.demo.domain.Nurse;
import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.Status;
import com.soen6841.demo.domain.User;
import com.soen6841.demo.service.NurseService;
import com.soen6841.demo.service.PatientService;
import com.soen6841.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class NurseController {
    @Autowired
    NurseService nurseService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    private PatientService patientService;

    @PostMapping("/nurse/registration")
    public String nurseRegister(Nurse nurse, Model model, HttpSession httpSession) {

        nurse.setRegisterStatus(Status.wating);
        nurse.setUserID((String) httpSession.getAttribute("userID"));
        nurseService.saveNurse(nurse);
        
        //
        Nurse nn = nurseService.getNurseByUserID(nurse.getUserID());
        User user = userService.getUserByUserID(nurse.getUserID());
        user.setUserType("nurse");
        user.setRegisterID(nn.getId());
        userService.saveUser(user);
        return "redirect:/index";
    }
    
    @GetMapping("/nurse_patient")
    public String getAllPatientUnderReview(Model model) {
        Iterable<Patient> patients = nurseService.getPatientByAppoinmentStatus(Status.wating);
        model.addAttribute("patients",patients);
        return "nurse_patient";
    }
    
    @GetMapping("/nurse_profile")
    public String getProfile(Model model) {
        return "nurse_profile";
    }
 
}
