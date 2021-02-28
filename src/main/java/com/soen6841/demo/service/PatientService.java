package com.soen6841.demo.service;

import com.soen6841.demo.dao.PatientRepository;
import com.soen6841.demo.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findOneById(id);
    }
    
    public Patient getPatientByUserID(String userID) {
        return patientRepository.findOneByUserID(userID);
    }
}
