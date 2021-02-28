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
        User manager = new User("admin","admin","manager",null);
        userList.add(manager);
        User nurse1 = new User("Linda","Linda","nurse", (long) 1);
        userList.add(nurse1);
        return userList;
    }

    private List<Doctor> getDoctorList(){
        List<Doctor> doctorList = new ArrayList<>();
        Doctor doctor1 = new Doctor("doctor1","Bob","a@gamil.com","A St,","111222333",new Date(19999999),"889", Status.wating);
        doctorList.add(doctor1);
        Doctor doctor2 = new Doctor("doctor2","Mike","b@gamil.com","B St,","888866666",new Date(19999999),"123", Status.accepted);
        doctorList.add(doctor2);
        Doctor doctor3 = new Doctor("doctor3","Lucy","c@gamil.com","C St,","768676678678",new Date(19999999),"188", Status.wating);
        doctorList.add(doctor3);
        return doctorList;
    }

    private List<Nurse> getNurseList(){
        List<Nurse> nurseList = new ArrayList<>();
        Nurse nurse1 = new Nurse("Linda","Linda","k@gamil.com","L St,","54768558",new Date(19999999),"1992", Status.wating);
        nurseList.add(nurse1);
        Nurse nurse2 = new Nurse("Bill","Bill","bikk@gamil.com","Bi St,","8888698699",new Date(288888888),"1236", Status.wating);
        nurseList.add(nurse2);
        return nurseList;
    }

    private List<Patient> getPatientList(){
        List<Patient> patientList = new ArrayList<>();
        Patient patient1 = new Patient("patient1","Will","ww@gamil.com","WWD St,","19999",new Date(19999999), Status.wating);
        patientList.add(patient1);
        return patientList;
    }
}
