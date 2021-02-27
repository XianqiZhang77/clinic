package com.soen6841.demo.controller;

import com.soen6841.demo.domain.Nurse;
import com.soen6841.demo.domain.Status;
import com.soen6841.demo.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NurseController {
    @Autowired
    NurseService nurseService;

    @PostMapping("/nurse/registration")
    public String nurseRegister(Nurse nurse, Model model, HttpSession httpSession) {
        if (nurse == null) {
            model.addAttribute("wrongInfo", "register fails");
            return "nurse_register";
        }
        nurse.setRegisterStatus(Status.wating);
        nurse.setUserID((String) httpSession.getAttribute("userID"));
        nurseService.saveNurse(nurse);
        return "redirect:/index";
    }

    @RequestMapping("/allnn")
    @ResponseBody
    public Iterable<Nurse> test() {
        return nurseService.getAllNurses();
    }
}
