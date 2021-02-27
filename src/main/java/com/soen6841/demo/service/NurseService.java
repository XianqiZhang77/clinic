package com.soen6841.demo.service;

import com.soen6841.demo.dao.NurseRepository;
import com.soen6841.demo.domain.Nurse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseService {

    @Autowired
    NurseRepository nurseRepository;

    public Iterable<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    public Nurse saveNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

}
