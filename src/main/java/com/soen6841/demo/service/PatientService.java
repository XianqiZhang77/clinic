package com.soen6841.demo.service;

import com.soen6841.demo.dao.PatientRepository;
import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private NurseService nurseService;

    @Autowired
    private DoctorService doctorService;

    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findOneById(id);
    }

    public boolean existsByUserID(String patientUserID) {
        return patientRepository.existsByUserID(patientUserID);
    }

    public Patient getPatientByUserID(String userID) {
        return patientRepository.findOneByUserID(userID);
    }

    public Patient saveQuestionAnswers(String patientId, String[] answer) {
        if(patientRepository.existsByUserID(patientId)){
            Patient patient = patientRepository.findOneByUserID(patientId);
            patient.setAnswerOne(answer[0]);
            patient.setAnswerTwo(answer[1]);
            patient.setAnswerThree(answer[2]);
            patient.setAnswerFour(answer[3]);
            patient.setAnswerFive(answer[4]);
            patient.setAnswerSix(answer[5]);
            patient.setAnswerSeven(answer[6]);
            patient.setSelfAssessmentTime(new Date());
            patient.setReviewStatus(Status.under_review);
        }
        return patientRepository.findOneByUserID(patientId);
    }

    public String[] getQuestionAnswers(String patientId) {
        String[] results = new String[7];
        if(patientRepository.existsByUserID(patientId)){
            Patient check = patientRepository.findOneByUserID(patientId);
            results[0] = check.getAnswerOne();
            results[1] = check.getAnswerTwo();
            results[2] = check.getAnswerThree();
            results[3] = check.getAnswerFour();
            results[4] = check.getAnswerFive();
            results[5] = check.getAnswerSix();
            results[6] = check.getAnswerSeven();
        }
        return results;
    }

    public Iterable<Patient> findAllAcceptedPatients() {
        List<Patient> patients = (List<Patient>) patientRepository.findPatientByRegisterStatus(Status.accepted);

        Collections.sort(patients, (p1, p2) -> {
            if (p1.getReviewStatus() == Status.unfinished) {
                return 1;
            }
            if (p2.getSelfAssessmentTime() == null) {
                return -1;
            }

            if (p1.getSelfAssessmentTime().before(p2.getSelfAssessmentTime())) {
	            if (p2.getReviewStatus() == Status.unfinished) {
	                return -1;
	            }
            }
            return -1;
        });
        return patients;
    }

    public void setReviewStatus(Patient patient, Status reviewStatus) {
        patient.setReviewStatus(reviewStatus);
        patientRepository.save(patient);
    }

    public void assignedToDoctor(String patientUserID, String nurseUserID, String doctorUserID) {

        if (!patientRepository.existsByUserID(patientUserID) || !nurseService.existsByUserID(nurseUserID) || !doctorService.existsByUserID(doctorUserID)) {
            return;
        }

        Patient patient = patientRepository.findOneByUserID(patientUserID);
        patient.setAssignee(doctorUserID);
        patient.setReviewer(nurseUserID);
        patientRepository.save(patient);


    }

    public Iterable<Patient> findByAssignee(String assignee) {
        List<Patient> patients = (List<Patient>) patientRepository.findPatientByAssignee(assignee);
        return patients;
    }
}
