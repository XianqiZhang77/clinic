package com.soen6841.demo.util;

import com.soen6841.demo.dao.AppointmentRepository;
import com.soen6841.demo.dao.DoctorRepository;
import com.soen6841.demo.dao.NurseRepository;
import com.soen6841.demo.dao.PatientRepository;
import com.soen6841.demo.dao.UserRepository;
import com.soen6841.demo.domain.User;
import com.soen6841.demo.domain.Appointment;
import com.soen6841.demo.domain.Doctor;
import com.soen6841.demo.domain.Nurse;
import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SystemListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(getDoctorList()!=null){
            doctorRepository.saveAll(getDoctorList());
        }
        if(getNurseList()!=null){
            nurseRepository.saveAll(getNurseList());
        }

        if(getPatientList()!=null){
            patientRepository.saveAll(getPatientList());
        }
        
        if(getUserList()!=null){
            userRepository.saveAll(getUserList());
        }
        
        if(getAppointmentList()!=null){
            appointmentRepository.saveAll(getAppointmentList());
        }
    }
    
    private List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        User manager = new User("admin",PasswordUtil.encrypt("admin"),"manager",null);
        userList.add(manager);
        User doctor1 = new User("Bob",PasswordUtil.encrypt("Bob"),"doctor", (long) 1);
        userList.add(doctor1);
        User doctor2 = new User("Mike",PasswordUtil.encrypt("Mike"),"doctor", (long) 2);
        userList.add(doctor2);
        User doctor3 = new User("Lucy",PasswordUtil.encrypt("Lucy"),"doctor", (long) 3);
        userList.add(doctor3);
        User nurse1 = new User("Linda",PasswordUtil.encrypt("Linda"),"nurse", (long) 1);
        userList.add(nurse1);
        User nurse2 = new User("Bill",PasswordUtil.encrypt("Bill"),"nurse", (long) 2);
        userList.add(nurse2);
        User nurse3 = new User("Alice",PasswordUtil.encrypt("Alice"),"nurse", (long) 3);
        userList.add(nurse3);
        User patient1 = new User("Will",PasswordUtil.encrypt("Will"),"patient", (long) 1);
        userList.add(patient1);
        User patient2 = new User("Peter",PasswordUtil.encrypt("Peter"),"patient", (long) 2);
        userList.add(patient2);
        User patient3 = new User("Ray",PasswordUtil.encrypt("Ray"),"patient", (long) 3);
        userList.add(patient3);
        User patient4 = new User("Tom",PasswordUtil.encrypt("Tom"),"patient", (long) 4);
        userList.add(patient4);
        User patient5 = new User("Brown",PasswordUtil.encrypt("Brown"),"patient", (long) 5);
        userList.add(patient5);
        return userList;
    }

    private List<Doctor> getDoctorList(){
        List<Doctor> doctorList = new ArrayList<>();
        Doctor doctor1 = new Doctor("Bob","Bob","a@gamil.com","A St,","111222333",new Date(19999999),"889", Status.waiting, new Date());
        doctorList.add(doctor1);
        Doctor doctor2 = new Doctor("Mike","Mike","b@gamil.com","B St,","888866666",new Date(19999999),"123", Status.accepted, new Date());
        doctorList.add(doctor2);
        Doctor doctor3 = new Doctor("Lucy","Lucy","c@gamil.com","C St,","768676678678",new Date(19999999),"188", Status.accepted, new Date());
        doctorList.add(doctor3);
        return doctorList;
    }

    private List<Nurse> getNurseList(){
        List<Nurse> nurseList = new ArrayList<>();
        Nurse nurse1 = new Nurse("Linda","Linda","k@gamil.com","L St,","54768558",new Date(19999999),"1992", Status.waiting, new Date());
        nurseList.add(nurse1);
        Nurse nurse2 = new Nurse("Bill","Bill","bikk@gamil.com","Bi St,","8888698699",new Date(288888888),"1236", Status.accepted, new Date());
        nurseList.add(nurse2);
        Nurse nurse3 = new Nurse("Alice","Alice","alice@gamil.com","Ai St,","88824328699",new Date(288888888),"1237", Status.accepted, new Date());
        nurseList.add(nurse3);
        return nurseList;
    }

    private List<Patient> getPatientList(){
        List<Patient> patientList = new ArrayList<>();
        Patient patient1 = new Patient();
        patient1.setUserID("Will").setFullName("Will").setEmail("ww@gmail.com").setAddress("WWD St").setPhoneNumber("19999").setBirthDate(new Date(19999999)).setRegisterStatus(Status.waiting).setReviewStatus(Status.unfinished).setRegisterTime(new Date(1616283200000L));
        patientList.add(patient1);
        Patient patient2 = new Patient();
        patient2.setUserID("Peter").setFullName("Peter").setEmail("peter@gmail.com").setAddress("WWD St").setPhoneNumber("12299991").setBirthDate(new Date(19999999)).setRegisterStatus(Status.accepted).setReviewStatus(Status.under_review).setSelfAssessmentTime(new Date()).setRegisterTime(new Date(1616715200000L))
                .setAnswerOne("Above 18 years").setAnswerTwo("Yes").setAnswerThree("No").setAnswerFour("Yes").setAnswerFive("No").setAnswerSix("Yes").setAnswerSeven("Yes");
        patientList.add(patient2);
        Patient patient3 = new Patient();
        patient3.setUserID("Ray").setFullName("Ray").setEmail("ray77@gmail.com").setAddress("W11WD St").setPhoneNumber("122993299").setBirthDate(new Date(29999999)).setRegisterStatus(Status.accepted).setReviewStatus(Status.assigned).setSelfAssessmentTime(new Date()).setReviewer("Alice").setAssignee("Mike").setRegisterTime(new Date(1616790200000L))
                .setAnswerOne("Above 18 years").setAnswerTwo("Yes").setAnswerThree("Yes").setAnswerFour("Yes").setAnswerFive("No").setAnswerSix("Yes").setAnswerSeven("Yes");
        patientList.add(patient3);
        Patient patient4 = new Patient();
        patient4.setUserID("Tom").setFullName("Tom").setEmail("tomcat@gmail.com").setAddress("KEET St").setPhoneNumber("122193299").setBirthDate(new Date(29993999)).setRegisterStatus(Status.accepted).setReviewStatus(Status.appointed).setSelfAssessmentTime(new Date()).setReviewer("Bill").setAssignee("Lucy").setRegisterTime(new Date(1626790200000L))
                .setAnswerOne("Above 18 years").setAnswerTwo("Yes").setAnswerThree("Yes").setAnswerFour("Yes").setAnswerFive("No").setAnswerSix("Yes").setAnswerSeven("Yes");
        patientList.add(patient4);
        Patient patient5 = new Patient();
        patient5.setUserID("Brown").setFullName("Brown").setEmail("brn@gmail.com").setAddress("Brown St").setPhoneNumber("13222122").setBirthDate(new Date(29993222)).setRegisterStatus(Status.accepted).setReviewStatus(Status.appointed).setSelfAssessmentTime(new Date()).setReviewer("Alice").setAssignee("Lucy").setRegisterTime(new Date(1607790200000L))
                .setAnswerOne("Above 18 years").setAnswerTwo("Yes").setAnswerThree("Yes").setAnswerFour("Yes").setAnswerFive("No").setAnswerSix("Yes").setAnswerSeven("Yes");
        patientList.add(patient5);
        return patientList;
    }
    
    private List<Appointment> getAppointmentList(){
        List<Appointment> appointmentList = new ArrayList<>();
        Appointment appointment1 = new Appointment("Tom","Tom","Alice","Alice","Please go hospital",new Date(), Status.appointed);
        Appointment appointment2 = new Appointment("Ray","Ray","Mike","Mike","Important",new Date(), Status.appointed);
        Appointment appointment3 = new Appointment("Brown","Brown","Alice","Alice","Please stay at home",new Date(), Status.appointed);
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
        appointmentList.add(appointment3);
        return appointmentList;
    }
}
