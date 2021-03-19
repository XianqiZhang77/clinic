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
import java.util.List;
import java.util.Map;

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
    
    @GetMapping("/nurse_profile")
    public String getProfile(Model model) {
        return "nurse_profile";
    }
    
    @RequestMapping("/nurse_assign")
    public String assignToDoctor(HttpSession httpSession, Assign assign) {
        String nurseUserID = (String) httpSession.getAttribute("userID");
        Nurse nurse = nurseService.getNurseByUserID(nurseUserID);
        String doctorUserID = assign.getDoctorUserID();
        String patientUserID = assign.getPatientUserID();
        patientService.assignedToDoctor(patientUserID, nurseUserID, doctorUserID);
        noticeService.addAssignNotice(patientUserID, nurseUserID, doctorUserID);
        return "redirect:/nurse_patient";
    }
    
    @RequestMapping("/nurseAppointment")
    public String makeAppointment(Appointment appointment, HttpSession httpSession) {
    	appointment.setAppointmentStatus(Status.available);
        appointmentService.saveAppointment(appointment);
        return "forward:/nurse_patient";
    }

    @RequestMapping("/rejectPatientByNurse/{patientId}")
    public String rejectPatientByNurse(@PathVariable String patientId, HttpSession httpSession,Model model) {
        Patient patient = patientService.getPatientByUserID(patientId);
        String userID = (String) httpSession.getAttribute("userID");
        patientService.setReviewStatus(patient,Status.rejected);
        noticeService.addRejectNoticeByNurse(patient.getUserID(), userID);
        return "redirect:/nurse_patient";
    }
}
