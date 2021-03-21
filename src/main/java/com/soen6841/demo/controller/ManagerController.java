package com.soen6841.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.soen6841.demo.domain.Doctor;
import com.soen6841.demo.domain.Nurse;
import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.Status;
import com.soen6841.demo.service.DoctorService;
import com.soen6841.demo.service.ManagerService;
import com.soen6841.demo.service.NurseService;
import com.soen6841.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private NurseService nurseService;

    @GetMapping("/getAllDoctorUnderReview")
    public String getAllDoctorUnderReview(Model model) {
        //Iterable<Doctor> doctors = managerService.getDoctorByRegisterStatus(Status.wating);
        Iterable<Doctor> doctors = managerService.getAllDoctors();
        model.addAttribute("doctors",doctors);
        return "manager_doctor";
    }

    @GetMapping("/getAllNurseUnderReview")
    public String getAllNurseUnderReview(Model model) {
        //Iterable<Nurse> nurses =  managerService.getNurseByRegisterStatus(Status.waiting);
        Iterable<Nurse> nurses = managerService.getAllNurse();
        model.addAttribute("nurses",nurses);
        return "manager_nurse";
    }

    @GetMapping("/getAllPatientUnderReview")
    public String getAllPatientUnderReview(Model model) {
        //Iterable<Patient> patients =   managerService.getPatientByRegisterStatus(Status.wating);
        Iterable<Patient> patients = managerService.getAllPatients();
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
        Iterable<Doctor> doctors = managerService.getDoctorByRegisterStatus(Status.waiting);
        model.addAttribute("doctors",doctors);
        return "redirect:/getAllDoctorUnderReview";
    }

    @RequestMapping("/rejectDoctor/{doctorId}")
    public String rejectDoctor(@PathVariable String doctorId, Model model){
        Doctor doctor = managerService.findDoctorById(doctorId);
        if(doctor==null){
            model.addAttribute("msg","can not find doctor");
        }else {
            managerService.rejectDoctor(doctor);
            doctorService.cancelAppointmentByManager(doctor.getUserID());
        }
        return "redirect:/getAllDoctorUnderReview";
    }

    @RequestMapping("/approveNurse/{nurseId}")
    public String approveNurse(@PathVariable String nurseId, Model model){
        Nurse nurse = managerService.findNurseById(nurseId);
        if(nurse==null){
            model.addAttribute("msg","can not find doctor");
        }else {
            managerService.approveNurse(nurse);
        }
        return "redirect:/getAllNurseUnderReview";
    }

    @RequestMapping("/rejectNurse/{nurseId}")
    public String rejectNurse(@PathVariable String nurseId, Model model){
        Nurse nurse = managerService.findNurseById(nurseId);
        if(nurse==null){
            model.addAttribute("msg","can not find doctor");
        }else {
            managerService.rejectNurse(nurse);
            nurseService.cancelAppointmentByManager(nurse.getUserID());
        }
        Iterable<Nurse> nurses =  managerService.getNurseByRegisterStatus(Status.waiting);
        model.addAttribute("nurses",nurses);
        return "redirect:/getAllNurseUnderReview";
    }

    @RequestMapping("/approvePatient/{patientId}")
    public String approvePatient(@PathVariable String patientId, Model model){
        Patient patient = managerService.findPatientById(patientId);
        if(patient==null){
            model.addAttribute("msg","can not find doctor");
        }else {
            managerService.approvePatient(patient);
        }
        Iterable<Patient> patients =   managerService.getPatientByRegisterStatus(Status.waiting);
        model.addAttribute("patients",patients);
        return "redirect:/getAllPatientUnderReview";
    }

    @RequestMapping("/rejectPatient/{patientId}")
    public String rejectPatient(@PathVariable String patientId, Model model){
        Patient patient = managerService.findPatientById(patientId);
        if(patient==null){
            model.addAttribute("msg","can not find doctor");
        }else {
            managerService.rejectPatient(patient);
            patientService.cancelAppointmentByManager(patient.getUserID());
        }
        Iterable<Patient> patients =   managerService.getPatientByRegisterStatus(Status.waiting);
        model.addAttribute("patients",patients);
        return "redirect:/getAllPatientUnderReview";
    }

    @RequestMapping(value = "report")
    @ResponseBody
    public List<Patient> report(@RequestParam String params, HttpSession httpSession, Model model){
        JSONArray jsonArray = JSON.parseArray(params);
        String[] result = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            result[i] = jsonArray.get(i).toString();
        }

        String start = result[0];
        String end = result[1];
        Iterable<Patient> patients = patientService.getAllPatients();
        List<Patient> patientList  = managerService.getPatientsBetweenStartAndEnd(start, end, patients);

        httpSession.setAttribute("patients",patientList);
        httpSession.setAttribute("size",patientList.size());
        return patientList;
    }
}
