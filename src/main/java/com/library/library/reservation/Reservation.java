package com.library.library.reservation;

import com.library.library.book.Book;
import com.library.library.member.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private Book book;
    private Member member;

    private LocalDateTime dateTime;

    private Boolean notified = false;

    public Reservation() {
    }

    public Reservation(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.dateTime = LocalDateTime.now();
        this.notified = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getNotified() {
        return notified;
    }

    public void setNotified(Boolean notified) {
        this.notified = notified;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", book=" + book +
                ", member=" + member +
                ", dateTime=" + dateTime +
                ", notified=" + notified +
                '}';
    }
}
