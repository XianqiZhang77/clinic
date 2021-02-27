package com.soen6841.demo.dao;

import com.soen6841.demo.domain.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    @Override
    Iterable<Doctor> findAll();

    Doctor findOneById(Long id);

}
