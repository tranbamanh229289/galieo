package com.manhtb.productservice.entity;

import com.manhtb.productservice.enumerate.BookType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Book extends Base{
    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private Double price;

    @Column(name = "isReady")
    private boolean isReady;

    @Enumerated(EnumType.STRING)
    private BookType bookType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
}
