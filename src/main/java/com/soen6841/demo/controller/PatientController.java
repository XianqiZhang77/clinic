package com.soen6841.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.soen6841.demo.domain.Appointment;
import com.soen6841.demo.domain.Notice;
import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.Status;
import com.soen6841.demo.domain.User;
import com.soen6841.demo.service.NoticeService;
import com.soen6841.demo.service.PatientService;
import com.soen6841.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Patient> getAllProjects() {
        return patientService.getAllPatients();
    }

    @PostMapping("/patient/registration")
    public String patientRegister(Patient patient, Model model, HttpSession httpSession) {
        patient.setRegisterStatus(Status.waiting);
        patient.setUserID((String) httpSession.getAttribute("userID"));
        patient.setRegisterTime(new Date());
        patient.setReviewStatus(Status.unfinished);
        Patient patient1 = patientService.savePatient(patient);

        User user = userService.getUserByUserID(patient.getUserID());
        user.setUserType("patient");
        user.setRegisterID(patient1.getId());
        userService.saveUser(user);
        return "redirect:/login";
    }

    @RequestMapping("/selfAssessment")
    public String selfAssessment(@RequestParam String params, HttpSession httpSession) {
        String patientId = (String) httpSession.getAttribute("userID");

        JSONArray jsonArray = JSON.parseArray(params);
        String[] result = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            result[i] = jsonArray.get(i).toString();
        }

        Patient p1 = patientService.saveQuestionAnswers(patientId,result);
        patientService.savePatient(p1);
        return "redirect:/assessment";
    }

    @RequestMapping("/assessmentResult")
    public String assessmentResult( Model model, HttpSession httpSession) {
        String patientId = (String) httpSession.getAttribute("userID");
        String[] results = patientService.getQuestionAnswers(patientId);
        model.addAttribute("resultOne",results[0]);
        model.addAttribute("resultTwo",results[1]);
        model.addAttribute("resultThree",results[2]);
        model.addAttribute("resultFour",results[3]);
        model.addAttribute("resultFive",results[4]);
        model.addAttribute("resultSix",results[5]);
        model.addAttribute("resultSeven",results[6]);
        return "assessment_result";
    }
    
    @RequestMapping("/getAssessmentResult/{patientId}")
    public String assessmentResult(@PathVariable String patientId, Model model) {
        String[] results = patientService.getQuestionAnswers(patientId);
        model.addAttribute("userID",patientId);
        model.addAttribute("resultOne",results[0]);
        model.addAttribute("resultTwo",results[1]);
        model.addAttribute("resultThree",results[2]);
        model.addAttribute("resultFour",results[3]);
        model.addAttribute("resultFive",results[4]);
        model.addAttribute("resultSix",results[5]);
        model.addAttribute("resultSeven",results[6]);
        return "patient_result";
    }

    @RequestMapping("/patient/main")
    public String jumpToPatientMain(Model model, HttpSession httpSession) {
        String userID = (String) httpSession.getAttribute("userID");
        Iterable<Notice> notices = noticeService.findNoticeByUserID(userID);
        model.addAttribute("notices", notices);
        return "patient_main";
    }
    
    @RequestMapping("/patient_appointment")
    public String patientAppointment(Model model, HttpSession httpSession) {
        String userID = (String) httpSession.getAttribute("userID");
        Iterable<Appointment> appointments = patientService.getAppointmentBypatientId(userID);
        model.addAttribute("appointments", appointments);
        return "patient_appointment";
    }
}