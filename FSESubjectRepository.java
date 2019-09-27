package com.fse.repository;

import com.fse.bean.Book;
import com.fse.bean.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FSESubjectRepository extends CrudRepository<Subject, String> {

    @Query("SELECT sub FROM subject s WHERE s.subject_id = :subjectID")
    Subject getSubject(@Param("subjectID") Long subjectID);

    @Query("delete FROM subject s WHERE s.subject_id = :subjectID")
    void deleteSubject(@Param("subjectID") Long subjectID);

}