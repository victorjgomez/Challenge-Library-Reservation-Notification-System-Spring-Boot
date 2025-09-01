package com.library.library.borrow;


import com.library.library.book.Book;
import com.library.library.book.BookService;
import com.library.library.member.Member;
import com.library.library.reservation.Reservation;
import com.library.library.reservation.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookService bookService;

    private final ReservationService reservationService;

    public BorrowService(BorrowRepository borrowRepository, BookService bookService, ReservationService reservationService) {
        this.borrowRepository = borrowRepository;
        this.bookService = bookService;
        this.reservationService = reservationService;
    }

    public Optional<Borrow> getByBookAndMember(Book book, Member member){
        return this.borrowRepository.findByBookAndMemberAndDeliver(book, member, false);
    }

    public synchronized Borrow createBorrow(Borrow borrow){
        this.bookService.decreaseStock(borrow.getBook());

        return borrowRepository.save(borrow);
    }

    public synchronized Borrow returnBook(Borrow borrow){
        this.bookService.increaseStock(borrow.getBook());

        borrow.setDeliver(true);

        Borrow returnBorrow = borrowRepository.save(borrow);
        this.callPendingNotifications(returnBorrow);

        return borrowRepository.save(borrow);
    }

    public Reservation callPendingNotifications(Borrow borrow){
        return reservationService.callPendingNotifications(borrow);
    }

    public List<Borrow> getAllByRangeDate(LocalDate from, LocalDate to){
        return borrowRepository.findAllByDeliverAndDateBetween(false, from, to);
    }

    public List<Borrow> getAllByRule(){
        LocalDate now = LocalDate.now();

        return borrowRepository.findAllByDeliver(false).stream()
                .filter(borrow -> !borrow.getDueDate().minusDays(2).isAfter(now))
                .toList();
    }

    public List<Member> getAllMemberWithOverDue(){
        LocalDate now = LocalDate.now();

        return borrowRepository.findAllByDeliverAndDateLessThanEqual(false, now).stream().map(Borrow::getMember).toList();
    }


}
