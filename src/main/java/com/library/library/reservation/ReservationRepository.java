package com.library.library.reservation;

import com.library.library.book.Book;
import com.library.library.borrow.Borrow;
import com.library.library.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    public List<Reservation> findByBookAndNotifiedOrderByDateTimeDesc(Book book, boolean notified);

    public List<Reservation> findByMemberAndNotifiedOrderByDateTimeDesc(Member member, boolean notified);
}
