package com.soen6841.demo.controller;

import com.soen6841.demo.domain.*;
import com.soen6841.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private NoticeService noticeService;

    @PostMapping("/doctor/registration")
    public String doctorRegister(Doctor doctor, Model model, HttpSession httpSession) {
        doctor.setRegisterStatus(Status.waiting);
        doctor.setUserID((String) httpSession.getAttribute("userID"));
        doctor.setRegisterTime(new Date());
        Doctor doctor1 = doctorService.saveDoctor(doctor);

        User user = userService.getUserByUserID(doctor.getUserID());
        user.setUserType("doctor");
        user.setRegisterID(doctor1.getId());
        userService.saveUser(user);
        return "redirect:/login";
    }
    
    @GetMapping("/doctor_patient")
    public String getAllPatients(Model model) {
        Iterable<Patient> patients = doctorService.getAllAcceptedPatients();
        model.addAttribute("patients", patients);
        return "doctor_patient";
    }
    
    @GetMapping("/doctor_profile")
    public String getDoctorProfile(Model model) {
        //Iterable<Patient> patients =   nurseService.getPatientByRegisterStatus(Status.wating);
        //model.addAttribute("patients",patients);
        return "doctor_profile";
    }

    @RequestMapping("/doctor_assigned")
    public String getAssignedPatients(Model model, HttpSession httpSession) {
        String userID = (String) httpSession.getAttribute("userID");
        Iterable<Patient> patients = patientService.findByAssignee(userID);
        model.addAttribute("patients", patients);
        return "doctor_assigned";
    }

    @RequestMapping("/rejectPatientByDoctor/{patientId}")
    public String rejectPatientByDoctor(@PathVariable String patientId, HttpSession httpSession) {
        Patient patient = patientService.getPatientByUserID(patientId);
        String userID = (String) httpSession.getAttribute("userID");
        patientService.setReviewStatus(patient,Status.rejected);
        noticeService.addRejectNoticeByDoctor(patient.getUserID(), userID);
        return "redirect:/doctor_patient";
    }

    @RequestMapping("/rejectPatientByDoctorAssigned/{patientId}")
    public String rejectPatientByDoctorAssigned(@PathVariable String patientId, HttpSession httpSession) {
        Patient patient = patientService.getPatientByUserID(patientId);
        String userID = (String) httpSession.getAttribute("userID");
        patientService.setReviewStatus(patient,Status.rejected);
        noticeService.addRejectNoticeByDoctor(patient.getUserID(), userID);
        return "redirect:/doctor_assigned";
    }

}
