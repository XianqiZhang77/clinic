package com.soen6841.demo.controller;

import com.soen6841.demo.domain.Doctor;
import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.Status;
import com.soen6841.demo.domain.User;
import com.soen6841.demo.service.DoctorService;
import com.soen6841.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private UserService userService;

    @PostMapping("/doctor/registration")
    public String doctorRegister(Doctor doctor, Model model, HttpSession httpSession) {
        doctor.setRegisterStatus(Status.wating);
        doctor.setUserID((String) httpSession.getAttribute("userID"));
        Doctor doctor1 = doctorService.saveDoctor(doctor);
        
        //
        //Doctor dd = doctorService.getDoctorByUserID(doctor.getUserID());
        User user = userService.getUserByUserID(doctor.getUserID());
        user.setUserType("doctor");
        user.setRegisterID(doctor1.getId());
        userService.saveUser(user);
        return "redirect:/index";
    }
    
    @GetMapping("/doctor_patient")
    public String getAllPatientUnderReview(Model model, HttpSession httpSession) {
        Iterable<Patient> patients = doctorService.getPatientByAssignee((String) httpSession.getAttribute("userID"));
        model.addAttribute("patients",patients);
        return "doctor_patient";
    }
    
    @GetMapping("/doctor_profile")
    public String getDoctorProfile(Model model) {
        //Iterable<Patient> patients =   nurseService.getPatientByRegisterStatus(Status.wating);
        //model.addAttribute("patients",patients);
        return "doctor_profile";
    }

}
