package com.soen6841.demo.controller;

import com.soen6841.demo.domain.Doctor;
import com.soen6841.demo.domain.Nurse;
import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.Status;
import com.soen6841.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/getAllDoctorUnderReview")
    public String getAllDoctorUnderReview(Model model) {
        Iterable<Doctor> doctors = managerService.getDoctorByRegisterStatus(Status.wating);
        model.addAttribute("doctors",doctors);
        return "manager_doctor";
    }

    @GetMapping("/getAllNurseUnderReview")
    public String getAllNurseUnderReview(Model model) {
        Iterable<Nurse> nurses =  managerService.getNurseByRegisterStatus(Status.wating);
        model.addAttribute("nurses",nurses);
        return "manager_nurse";
    }

    @GetMapping("/getAllPatientUnderReview")
    public String getAllPatientUnderReview(Model model) {
        Iterable<Patient> patients =   managerService.getPatientByRegisterStatus(Status.wating);
        model.addAttribute("patients",patients);
        return "manager_patient";
    }


    @RequestMapping("/approveDoctor/{doctorId}")
    public String approveDoctor(@PathVariable String doctorId, Model model){
        Doctor doctor = managerService.findDoctorById(doctorId);
        if(doctor==null){
            model.addAttribute("msg","can not find doctor");
        }else {
            managerService.approveDoctor(doctor);
        }
        Iterable<Doctor> doctors = managerService.getDoctorByRegisterStatus(Status.wating);
        model.addAttribute("doctors",doctors);
        return "manager_doctor";
    }

    @RequestMapping("/rejectDoctor/{doctorId}")
    public String rejectDoctor(@PathVariable String doctorId, Model model){
        Doctor doctor = managerService.findDoctorById(doctorId);
        if(doctor==null){
            model.addAttribute("msg","can not find doctor");
        }else {
            managerService.rejectDoctor(doctor);
        }
        Iterable<Doctor> doctors = managerService.getDoctorByRegisterStatus(Status.wating);
        model.addAttribute("doctors",doctors);
        return "manager_doctor";
    }

    @RequestMapping("/approveNurse/{nurseId}")
    public String approveNurse(@PathVariable String nurseId, Model model){
        Nurse nurse = managerService.findNurseById(nurseId);
        if(nurse==null){
            model.addAttribute("msg","can not find doctor");
        }else {
            managerService.approveNurse(nurse);
        }
        Iterable<Nurse> nurses =  managerService.getNurseByRegisterStatus(Status.wating);
        model.addAttribute("nurses",nurses);
        return "manager_nurse";
    }

    @RequestMapping("/rejectNurse/{nurseId}")
    public String rejectNurse(@PathVariable String nurseId, Model model){
        Nurse nurse = managerService.findNurseById(nurseId);
        if(nurse==null){
            model.addAttribute("msg","can not find doctor");
        }else {
            managerService.rejectNurse(nurse);
        }
        Iterable<Nurse> nurses =  managerService.getNurseByRegisterStatus(Status.wating);
        model.addAttribute("nurses",nurses);
        return "manager_nurse";
    }

    @RequestMapping("/approvePatient/{patientId}")
    public String approvePatient(@PathVariable String patientId, Model model){
        Patient patient = managerService.findPatientById(patientId);
        if(patient==null){
            model.addAttribute("msg","can not find doctor");
        }else {
            managerService.approvePatient(patient);
        }
        Iterable<Patient> patients =   managerService.getPatientByRegisterStatus(Status.wating);
        model.addAttribute("patients",patients);
        return "manager_patient";
    }

    @RequestMapping("/rejectPatient/{patientId}")
    public String rejectPatient(@PathVariable String patientId, Model model){
        Patient patient = managerService.findPatientById(patientId);
        if(patient==null){
            model.addAttribute("msg","can not find doctor");
        }else {
            managerService.rejectPatient(patient);
        }
        Iterable<Patient> patients =   managerService.getPatientByRegisterStatus(Status.wating);
        model.addAttribute("patients",patients);
        return "manager_patient";
    }
}
