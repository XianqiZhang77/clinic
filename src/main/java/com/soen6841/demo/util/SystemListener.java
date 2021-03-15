package com.soen6841.demo.util;

import com.soen6841.demo.dao.DoctorRepository;
import com.soen6841.demo.dao.NurseRepository;
import com.soen6841.demo.dao.PatientRepository;
import com.soen6841.demo.dao.UserRepository;
import com.soen6841.demo.domain.User;
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
        return userList;
    }

    private List<Doctor> getDoctorList(){
        List<Doctor> doctorList = new ArrayList<>();
        Doctor doctor1 = new Doctor("Bob","Bob","a@gamil.com","A St,","111222333",new Date(19999999),"889", Status.wating, new Date());
        doctorList.add(doctor1);
        Doctor doctor2 = new Doctor("Mike","Mike","b@gamil.com","B St,","888866666",new Date(19999999),"123", Status.accepted, new Date());
        doctorList.add(doctor2);
        Doctor doctor3 = new Doctor("Lucy","Lucy","c@gamil.com","C St,","768676678678",new Date(19999999),"188", Status.wating, new Date());
        doctorList.add(doctor3);
        return doctorList;
    }

    private List<Nurse> getNurseList(){
        List<Nurse> nurseList = new ArrayList<>();
        Nurse nurse1 = new Nurse("Linda","Linda","k@gamil.com","L St,","54768558",new Date(19999999),"1992", Status.wating, new Date());
        nurseList.add(nurse1);
        Nurse nurse2 = new Nurse("Bill","Bill","bikk@gamil.com","Bi St,","8888698699",new Date(288888888),"1236", Status.wating, new Date());
        nurseList.add(nurse2);
        Nurse nurse3 = new Nurse("Alice","Alice","alice@gamil.com","Ai St,","88824328699",new Date(288888888),"1237", Status.accepted, new Date());
        nurseList.add(nurse3);
        return nurseList;
    }

    private List<Patient> getPatientList(){
        List<Patient> patientList = new ArrayList<>();
        Patient patient1 = new Patient("Will","Will","ww@gamil.com","WWD St,","19999",new Date(19999999), Status.wating, new Date(), Status.under_review);
        patientList.add(patient1);
        Patient patient2 = new Patient("Peter","Peter","peter@gamil.com","WWD St,","19999",new Date(19999999), Status.accepted, new Date(), "18 years+" , "No", "No" ,"No", new Date(), Status.under_review);
        patientList.add(patient2);
        Patient patient3 = new Patient("Ray", "Ray", "Ray@gmail.com", "Du Fort", "43234545", new Date(19999999), Status.accepted, new Date(), "18 years+" , "Yes", "Yes" ,"Yes", new Date(), Status.reviewed, "Mike", "Alice");
        return patientList;
    }
}
