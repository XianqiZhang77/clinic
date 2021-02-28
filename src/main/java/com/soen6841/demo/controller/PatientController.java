package com.soen6841.demo.controller;

import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.Status;
import com.soen6841.demo.domain.User;
import com.soen6841.demo.service.PatientService;
import com.soen6841.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Patient> getAllProjects() {
        return patientService.getAllPatients();
    }

    @PostMapping("/patient/registration")
    public String patientRegister(Patient patient, Model model, HttpSession httpSession) {
        patient.setRegisterStatus(Status.wating);
        patient.setUserID((String) httpSession.getAttribute("userID"));
        patientService.savePatient(patient);
        
        //
        Patient pp = patientService.getPatientByUserID(patient.getUserID());
        User user = userService.getUserByUserID(patient.getUserID());
        user.setUserType("patient");
        user.setRegisterID(pp.getId());
        userService.saveUser(user);
        return "redirect:/index";
    }

}