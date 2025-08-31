package com.library.library.borrow;

import com.library.library.book.Book;
import com.library.library.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    public Optional<Borrow> findByBookAndMemberAndDelivery(Book book, Member member, boolean delivery);

    public List<Borrow> findAllByDeliverAndDateBetween(boolean deliver, LocalDate from, LocalDate to);

    public List<Borrow> findAllByDeliverAndDateLessThanEqual(boolean deliver, LocalDate date);
}
