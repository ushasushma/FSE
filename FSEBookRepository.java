package com.fse.repository;

import com.fse.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository
public interface FSEBookRepository extends CrudRepository<Book, String> {

    @Query("SELECT bk FROM book b WHERE b.book_id = :bookID")
    Book getBook(@Param("bookID") Long bookID);

    @Query("delete FROM book b WHERE b.book_id = :bookID")
    void deleteBook(@Param("bookID") Long bookID);

}