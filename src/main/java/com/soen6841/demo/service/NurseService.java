package com.soen6841.demo.service;

import com.soen6841.demo.dao.NurseRepository;
import com.soen6841.demo.dao.PatientRepository;
import com.soen6841.demo.domain.Nurse;
import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseService {

    @Autowired
    NurseRepository nurseRepository;
    
    @Autowired
    private PatientRepository patientRepository;

    public Iterable<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    public Nurse saveNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }
    
    public Nurse getNurseById(Long id) {
        return nurseRepository.findOneById(id);
    }

    public Nurse getNurseByUserID(String userID) {
        return nurseRepository.findOneByUserID(userID);
    }
    
    public Iterable<Patient> getPatientByAppoinmentStatus(Status registerStatus) {
        return patientRepository.findPatientByAppointmentStatus(registerStatus);
    }
}
