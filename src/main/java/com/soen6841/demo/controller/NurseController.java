package com.soen6841.demo.controller;

import com.soen6841.demo.domain.*;
import com.soen6841.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class NurseController {
    @Autowired
    NurseService nurseService;
    
    @Autowired
    UserService userService;

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/nurse/registration")
    public String nurseRegister(Nurse nurse, Model model, HttpSession httpSession) {

        nurse.setRegisterStatus(Status.wating);
        String userID = (String) httpSession.getAttribute("userID");
        nurse.setUserID(userID);
        nurse.setRegisterTime(new Date());
        Nurse nurse1 = nurseService.saveNurse(nurse);

        User user = userService.getUserByUserID(nurse.getUserID());
        user.setUserType("nurse");
        user.setRegisterID(nurse1.getId());
        userService.saveUser(user);
        return "redirect:/index";
    }
    
    @GetMapping("/nurse_patient")
    public String getAllPatient(Model model) {
        Iterable<Patient> patients = nurseService.getAllAcceptedPatients();
        Iterable<Doctor> doctors = nurseService.getAllAcceptedDoctors();
        model.addAttribute("patients", patients);
        model.addAttribute("doctors", doctors);
        return "nurse_patient";
    }
    
    @GetMapping("/nurse_profile")
    public String getProfile(Model model) {
        return "nurse_profile";
    }
    
    @RequestMapping("/nurse_assign/{patientUserID}/{doctorUserID}/{nurseUserID}")
    public String assignToDoctor(HttpSession httpSession, @PathVariable String patientUserID, @PathVariable String doctorUserID, @PathVariable String nurseUserID) {
        //String userID = (String) httpSession.getAttribute("userID");
        Nurse nurse = nurseService.getNurseByUserID(nurseUserID);
        Patient patient = patientService.getPatientByUserID(patientUserID);
        Doctor doctor = doctorService.getDoctorByUserID(doctorUserID);
        System.out.println(nurse.getFullName());
        System.out.println(patient.getFullName());
        System.out.println(doctor.getFullName());
        appointmentService.addNewAppointmentByAssign(patientUserID, doctorUserID, nurseUserID);
        return "forward:/nurse_patient";
    }

}
