package com.soen6841.demo.controller;

import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.Status;
import com.soen6841.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/all")
    public Iterable<Patient> getAllProjects() {
        return patientService.getAllPatients();
    }

    @PostMapping("/patient/registration")
    public String patientRegister(Patient patient, Model model, HttpSession httpSession) {
        if (patient == null) {
            model.addAttribute("wrongInfo", "register fails");
            return "patient_register";
        }
        patient.setRegisterStatus(Status.wating);
        patient.setUserID((String) httpSession.getAttribute("userID"));
        patientService.savePatient(patient);
        return "redirect:/index";
    }
    @RequestMapping("/allpp")
    @ResponseBody
    public Iterable<Patient> test() {
        return patientService.getAllPatients();
    }
}