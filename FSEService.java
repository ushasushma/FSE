package com.fse.service;

import com.fse.bean.Book;
import com.fse.bean.Subject;
import com.fse.repository.FSEBookRepository;
import com.fse.repository.FSESubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class FSEService {
    @Autowired
    FSEBookRepository fseBookRepository;

    @Autowired
    FSESubjectRepository subjectRepository;


    @Transactional
    public Book getBook(Long bookID) {
        return fseBookRepository.getBook(bookID);
    }

    @Transactional
    public void deleteBook(Long bookID) {
        fseBookRepository.deleteBook(bookID);
    }

    @Transactional
    public boolean addBook(Book book) {
        return fseBookRepository.save(book) != null;
    }

    @Transactional
    public Subject getSubject(Long subjectID) {
        return subjectRepository.getSubject(subjectID);
    }

    @Transactional
    public void deleteSubject(Long subjectID) {
        subjectRepository.deleteSubject(subjectID);
    }

    @Transactional
    public boolean addSubject(Subject subject) {
        return subjectRepository.save(subject) != null;
    }

}