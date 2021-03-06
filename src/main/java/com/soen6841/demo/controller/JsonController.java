package com.soen6841.demo.controller;

import com.soen6841.demo.domain.Doctor;
import com.soen6841.demo.domain.Status;
import com.soen6841.demo.domain.User;
import com.soen6841.demo.service.DoctorService;
import com.soen6841.demo.service.PatientService;
import com.soen6841.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
 
/**
 * 测试.
 * @author Administrator
 *
 */
@RestController
public class JsonController {
	
    @Autowired
    private PatientService patientService;
    
    @RequestMapping("/getAssessmentResult/{patientId}")
    public String[] assessmentResult(@PathVariable String patientId, Model model) {
        return patientService.getQuestionAnswers(patientId);
    }

}