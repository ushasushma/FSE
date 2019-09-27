package com.fse.controller;

import com.fse.bean.*;
import com.fse.dao.impl.BookDAOImpl;
import com.fse.dao.impl.SubjectDAOImpl;
import com.fse.service.FSEService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Controller
public class FSEController {

    @Autowired
    FSEService fseService;

    private Gson gson;

    public FSEController() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gson = gsonBuilder.setPrettyPrinting().create();
    }


    @RequestMapping(value = "/addbook", method = RequestMethod.POST)

    public String addBook(@RequestBody String fseBook, ModelMap model) {
        try {
            fseBook = java.net.URLDecoder.decode(fseBook, StandardCharsets.UTF_8.name());
            fseBook = fseBook.substring(fseBook.indexOf("=") + 1, fseBook.indexOf("&"));
            fseService.addBook(gson.fromJson(fseBook, Book.class));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        model.addAttribute("result", "Book added successfully!");
        return "result";
    }

    @RequestMapping(value = "/getbook", method = RequestMethod.GET)
    public String getBook(@RequestParam("bookID") String bookID, ModelMap model) {
        Book book = fseService.getBook(Long.parseLong(bookID));
        model.addAttribute("id", book.getBookID());
        model.addAttribute("title", book.getTitle());
        model.addAttribute("price", book.getPrice());
        model.addAttribute("volume", book.getVolume());
        model.addAttribute("publishdate", book.getPublishDate());
        return "bookresult";
    }

    @RequestMapping(value = "/deletebook", method = RequestMethod.GET)
    public String deleteBook(@RequestParam("bookID") String bookID, ModelMap model) {
        fseService.deleteBook(Long.parseLong(bookID));

        model.addAttribute("result", "Book deleted successfully!");
        return "result";
    }

    @RequestMapping(value = "/addsubject", method = RequestMethod.POST)
    public String addSubject(@RequestBody String fseSubject, ModelMap model) {
        try {
            fseSubject = java.net.URLDecoder.decode(fseSubject, StandardCharsets.UTF_8.name());
            fseSubject = fseSubject.substring(fseSubject.indexOf("=") + 1, fseSubject.indexOf("&"));
            fseService.addSubject(gson.fromJson(fseSubject, Subject.class));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        model.addAttribute("result", "Subject added successfully!");
        return "result";
    }

    @RequestMapping(value = "/getsubject", method = RequestMethod.GET)
    public String getSubject(@RequestParam("subjectID") String subID, ModelMap model) {
        Subject subject = fseService.getSubject(Long.parseLong(subID));

        model.addAttribute("id", subject.getSubjectID());
        model.addAttribute("title", subject.getSubTitle());
        model.addAttribute("duration", subject.getDurationInHours());
        int count = 0;
        for(Book book : subject.getBookSet()){
            model.addAttribute("book"+count+"title", book.getTitle());
            model.addAttribute("book"+count+"id", book.getBookID());
            model.addAttribute("book"+count+"price", book.getPrice());
            model.addAttribute("book"+count+"volume", book.getVolume());
            model.addAttribute("book"+count+"publishdate", book.getPublishDate());
            count++;
        }


        return "subjectresult";
    }

    @RequestMapping(value = "/deletesubject", method = RequestMethod.GET)
    public String deleteSubject(@RequestParam("subjectID") String subID, ModelMap model) {
        fseService.deleteSubject(Long.parseLong(subID));

        model.addAttribute("result", "Subject deleted successfully!");

        return "result";
    }

}

