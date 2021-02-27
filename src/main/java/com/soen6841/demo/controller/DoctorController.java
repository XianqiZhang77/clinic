package com.soen6841.demo.controller;

import com.soen6841.demo.domain.Doctor;
import com.soen6841.demo.domain.Status;
import com.soen6841.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.Doc;
import javax.servlet.http.HttpSession;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctor/registration")
    public String doctorRegister(Doctor doctor, Model model, HttpSession httpSession) {
        if (doctor == null) {
            model.addAttribute("wrongInfo", "register fails");
            return "doctor_register";
        }
        doctor.setRegisterStatus(Status.wating);
        doctor.setUserID((String) httpSession.getAttribute("userID"));
        doctorService.saveDoctor(doctor);
        return "redirect:/index";
    }

    @RequestMapping("/alldd")
    @ResponseBody
    public Iterable<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}
