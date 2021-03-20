package com.soen6841.demo.controller;

import com.soen6841.demo.domain.*;
import com.soen6841.demo.service.*;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

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

    @Autowired
    NoticeService noticeService;

    @PostMapping("/nurse/registration")
    public String nurseRegister(Nurse nurse, Model model, HttpSession httpSession) {

        nurse.setRegisterStatus(Status.waiting);
        String userID = (String) httpSession.getAttribute("userID");
        nurse.setUserID(userID);
        nurse.setRegisterTime(new Date());
        Nurse nurse1 = nurseService.saveNurse(nurse);

        User user = userService.getUserByUserID(nurse.getUserID());
        user.setUserType("nurse");
        user.setRegisterID(nurse1.getId());
        userService.saveUser(user);
        return "redirect:/login";
    }
    
    @GetMapping("/nurse_patient")
    public String getAllPatient(Model model) {
        Iterable<Patient> patients = nurseService.getAllAcceptedPatients();
        Iterable<Doctor> doctors = nurseService.getAllAcceptedDoctors();
        model.addAttribute("patients", patients);
        model.addAttribute("doctors", doctors);
        return "nurse_patient";
    }
    
    @RequestMapping("/nurse_profile")
    public String getAppointment(Model model, HttpSession httpSession) {
    	String userID = (String) httpSession.getAttribute("userID");   												
    	Iterable<Appointment> apppointments = appointmentService.getAllAssignedByHealthCareID(userID);
        model.addAttribute("appointments",apppointments);
        return "nurse_profile";
    }
    
    @RequestMapping("/nurse_assign")
    public String assignToDoctor(HttpSession httpSession, Assign assign) {
        String nurseUserID = (String) httpSession.getAttribute("userID");
        String doctorUserID = assign.getDoctorUserID();
        String patientUserID = assign.getPatientUserID();
        nurseService.assignPatientToDoctor(patientUserID, nurseUserID, doctorUserID);
        return "redirect:/nurse_patient";
    }
    
    
    @RequestMapping("/nurseAppointment")
    public String makeAppointment(Appointment appointment, HttpSession httpSession) {
    	String nurseUserID = (String) httpSession.getAttribute("userID");
    	nurseService.MakeAppointment(appointment, nurseUserID);
        return "forward:/nurse_patient";
    }
    
    @RequestMapping("/nurseCancelAppointment/{Id}")
    public String cancelAppointment(@PathVariable String Id, Model model) {
    	nurseService.CancelAppointment(Id);
        return "redirect:/nurse_profile";
    }

    @RequestMapping("/rejectPatientByNurse/{patientId}")
    public String rejectPatientByNurse(@PathVariable String patientId, HttpSession httpSession,Model model) {
        Patient patient = patientService.getPatientByUserID(patientId);
        String userID = (String) httpSession.getAttribute("userID");
        patientService.setReviewStatus(patient,Status.rejected);
        return "redirect:/nurse_patient";
    }
}
