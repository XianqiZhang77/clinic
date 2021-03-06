package com.soen6841.demo.service;

import com.soen6841.demo.dao.DoctorRepository;
import com.soen6841.demo.dao.NurseRepository;
import com.soen6841.demo.dao.PatientRepository;
import com.soen6841.demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private PatientRepository patientRepository;
    

    public Iterable<Doctor> getDoctorByRegisterStatus(Status registerStatus) {
        return doctorRepository.findDoctorByRegisterStatus(registerStatus);
    }

    public Iterable<Nurse> getNurseByRegisterStatus(Status registerStatus) {
        return nurseRepository.findNurseByRegisterStatus(registerStatus);
    }

    public Iterable<Patient> getPatientByRegisterStatus(Status registerStatus) {
        return patientRepository.findPatientByRegisterStatus(registerStatus);
    }

    public Doctor findDoctorById(String doctorId){
        return doctorRepository.findOneById(Long.valueOf(doctorId));
    }

    public Nurse findNurseById(String nurseId){
        return nurseRepository.findOneById(Long.valueOf(nurseId));
    }

    public Patient findPatientById(String patientId){
        return patientRepository.findOneById(Long.valueOf(patientId));
    }


    public void approveDoctor(Doctor doctor) {
        doctor.setRegisterStatus(Status.accepted);
        doctorRepository.save(doctor);
    }

    public void rejectDoctor(Doctor doctor) {
        doctor.setRegisterStatus(Status.rejected);
        doctorRepository.save(doctor);
    }

    public void approveNurse(Nurse nurse) {
        nurse.setRegisterStatus(Status.accepted);
        nurseRepository.save(nurse);
    }

    public void rejectNurse(Nurse nurse) {
        nurse.setRegisterStatus(Status.rejected);
        nurseRepository.save(nurse);
    }

    public void approvePatient(Patient patient) {
        patient.setRegisterStatus(Status.accepted);
        patientRepository.save(patient);
    }

    public void rejectPatient(Patient patient) {
        patient.setRegisterStatus(Status.rejected);
        patientRepository.save(patient);
    }

    public Iterable<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Iterable<Nurse> getAllNurse() {
        return nurseRepository.findAll();
    }

    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> getPatientsBetweenStartAndEnd(String start, String end, Iterable<Patient> patients) {

        start = start.replaceAll("/","-");
        end = end.replaceAll("/","-");
        List<Patient> patientList = new ArrayList<>();
        for(Patient patient:patients){
            String registerTime = patient.getRegisterTime().toString();
            if((registerTime.compareTo(start)>=0)&&(registerTime.compareTo(end)<=0)){
                patientList.add(patient);
            }
        }
        return  patientList;
    }

}
