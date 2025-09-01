package com.library.library.borrow;

import com.library.library.book.Book;
import com.library.library.member.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
    public Optional<Borrow> findByBookAndMemberAndDeliver(Book book, Member member, boolean deliver);

    public List<Borrow> findAllByDeliverAndDateBetween(boolean deliver, LocalDate from, LocalDate to);

    public List<Borrow> findAllByDeliverAndDateLessThanEqual(boolean deliver, LocalDate date);


    public List<Borrow> findAllByDeliver(boolean deliver);
  
    @Query("SELECT b.book FROM Borrow b GROUP BY b.book ORDER BY COUNT(b) DESC")
    List<Book> findTopBorrowedBooks(Pageable pageable);

}
