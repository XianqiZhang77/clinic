package com.soen6841.demo.controller;

import com.soen6841.demo.domain.Patient;
import com.soen6841.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/all")
    public Iterable<Patient> getAllProjects() {
        return patientService.getAllPatients();
    }

    @PostMapping("")
    public ResponseEntity<Patient> createNewProject(@RequestBody Patient patient) {
        Patient patient1 = patientService.savePatient(patient);
        return new ResponseEntity<Patient>(patient, HttpStatus.CREATED);
    }
}