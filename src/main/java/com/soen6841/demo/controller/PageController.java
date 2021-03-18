package com.soen6841.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class PageController {
    
	/*
	 * 网站主页
	 */
    @RequestMapping("/index")
    public String indexPage() {
        return "main_page";
    }
    @RequestMapping("/")
    public String index() {
        return "forward:/index";
    }
    
	/*
	 * 登录页
	 * *用户名，密码
	 */
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    /*
     * 账号创建页面
     * *用户名，密码
     */
    @RequestMapping("/create_account")
    public String creatAccountPage() {
        return "create_account";
    }
    
    /*
     * 账号创建成功自动跳转页面
     */
    @RequestMapping("/create_success")
    public String creatSuccessPage() {
        return "create_success";
    }
    
    /*
     * 用户选择注册角色页面
     * *选择医生、护士、患者
     */
    @RequestMapping("/type")
    public String typePage() {
        return "type";
    }
    
    /*
     * 用户填写患者信息页面
     */
    @RequestMapping("/patient_register")
    public String patientRegisterPage() {
        return "patient_register";
    }
    
    /*
     * 用户填写医生信息页面
     */
    @RequestMapping("/doctor_register")
    public String doctorRegisterPage() {
        return "doctor_register";
    }
    
    /*
     * 用户填写护士信息页面
     */
    @RequestMapping("/nurse_register")
    public String nurseRegisterPage() {
        return "nurse_register";
    }
    
    /*
     * 用户填写信息成功提交跳转页面
     */
    @RequestMapping("/register_success")
    public String registerSuccessPage() {
        return "register_success";
    }
    
    /*
     * 已批准为患者用户登录后主页
     */
    @RequestMapping("/patient")
    public String patientPage() {
        return "patient";
    }
    
    /*
     * 已批准为医生用户登录后主页
     */
    @RequestMapping("/doctor")
    public String doctorPage() {
        return "doctor";
    }
    
    /*
     * 已批准为护士用户登录后主页
     */
    @RequestMapping("/nurse")
    public String nursePage() {
        return "nurse_profile";
    }


    @RequestMapping("/assessment")
    public String assessmentPage() {
        return "assessment";
    }

    @RequestMapping("/enter_assessment")
    public String enterAssessmentPage() {
        return "enter_assessment";
    }

    @RequestMapping("/advise")
    public String advisePage() {
        return "advise";
    }

}