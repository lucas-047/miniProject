package com.library.entities;

import jakarta.persistence.Entity;

import java.time.LocalDate;

public class BookWithAdditionalData {
    private Book book;
    private LocalDate dueDate;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


}
