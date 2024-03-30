package com.library.entities;

import jakarta.persistence.*;
import org.assertj.core.internal.BinaryDiff;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "book")
public class Book {

    private String bookName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="bookId")
    private int bookId;
    private int CopyId;


    private String AuthorName;
    private String Publisher;
    private String Branch;
    private LocalDate PublishDate;
    private int BookStatus;

    private String Version;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCopyId() {
        return CopyId;
    }

    public void setCopyId(int copyId) {
        CopyId = copyId;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public LocalDate getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        PublishDate = publishDate;
    }

    public int getBookStatus() {
        return BookStatus;
    }

    public void setBookStatus(int bookStatus) {
        BookStatus = bookStatus;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", bookId='" + bookId + '\'' +
                ", CopyId='" + CopyId + '\'' +
                ", AuthorName='" + AuthorName + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", Branch='" + Branch + '\'' +
                ", PublishDate=" + PublishDate +
                ", BookStatus=" + BookStatus +
                ", Version='" + Version + '\'' +
                '}';
    }
}
