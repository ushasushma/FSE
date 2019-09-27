package com.fse.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "book")
public class Book implements Serializable{

    private static final long serialVersionUID = -7788619177798333712L;

    @Id
    @Column(name="book_id")
    private long bookID;
    @Column(name="book_title")
    private String title;
    @Column(name="book_price")
    private double price;
    @Column(name="book_volume")
    private int volume;
    @Column(name="publish_date")
    @Convert(converter = LocalDateConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    public Book(){

    }

    public Book(long bookID,String title, double price, int volume, LocalDate publishDate){
        this.bookID = bookID;
        this.title = title;
        this.price = price;
        this.volume = volume;
        this.publishDate = publishDate;
    }

    public LocalDate getPublishDate() {
        return publishDate;

    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }
}