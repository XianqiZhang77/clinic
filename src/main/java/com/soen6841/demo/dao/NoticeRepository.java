package com.soen6841.demo.dao;

import com.soen6841.demo.domain.Notice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    Iterable<Notice> findByPatientUserID(String patientUserID);

}
