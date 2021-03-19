package com.soen6841.demo.controller;

import com.soen6841.demo.domain.*;
import com.soen6841.demo.service.AppointmentService;
import com.soen6841.demo.service.DoctorService;
import com.soen6841.demo.service.PatientService;
import com.soen6841.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String getAppointment(Model model, HttpSession httpSession) {
    	String userID = (String) httpSession.getAttribute("userID");   												
    	Iterable<Appointment> apppointments = appointmentService.getAllAssignedByHealthCareID(userID);
        model.addAttribute("appointments",apppointments);
        return "doctor_profile";
    }

    @RequestMapping("/doctor_assigned")
    public String getAssignedPatients(Model model, HttpSession httpSession) {
        String userID = (String) httpSession.getAttribute("userID");
        Iterable<Patient> patients = patientService.findByAssignee(userID);
        model.addAttribute("patients", patients);
        return "doctor_assigned";
    }
    
    //
    @RequestMapping("/doctorAppointment")
    public String makeAppointment(Appointment appointment, HttpSession httpSession) {
    	String doctorUserID = (String) httpSession.getAttribute("userID");
    	appointment.setAppointmentStatus(Status.available);
    	User user = userService.getUserByUserID(doctorUserID);
    	Doctor doctor = doctorService.getDoctorById(user.getRegisterID());
    	User patient_user = userService.getUserByUserID(appointment.getPatientUserID());
    	Patient patient = patientService.getPatientById(patient_user.getRegisterID());
    	patient.setReviewStatus(Status.accepted);
    	patientService.savePatient(patient);
    	appointment.setHealthCareID(doctorUserID);
    	appointment.setHealthCareName(doctor.getFullName());
        appointmentService.saveAppointment(appointment);
        return "forward:/doctor_assigned";
    }

}
