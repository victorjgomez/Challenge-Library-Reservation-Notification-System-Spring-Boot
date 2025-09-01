package com.library.library.reservation;


import com.library.library.book.Book;
import com.library.library.book.BookService;
import com.library.library.borrow.Borrow;
import com.library.library.member.Member;
import com.library.library.member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReservationResource {

    private final ReservationService reservationService;

    private final BookService bookService;

    private final MemberService memberService;

    public ReservationResource(ReservationService reservationService, BookService bookService, MemberService memberService) {
        this.reservationService = reservationService;
        this.bookService = bookService;
        this.memberService = memberService;
    }


    @PostMapping("/reserve")
    public ResponseEntity<?> reserve(@RequestParam Long memberId, @RequestParam String isbn){
        Optional<Book> bookOptional = bookService.getBook(isbn);
        Optional<Member> memberOptional = memberService.getMember(memberId);

        if (bookOptional.isEmpty() || memberOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("memberId or isbn not found");
        }

        Book book = bookOptional.get();
        if (book.getStock() > 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Book is currently in stock");
        }

        Reservation reservation = reservationService.createReservation(new Reservation(book,
                memberOptional.get()));

        return ResponseEntity.status(HttpStatus.OK).body(reservation);
    }


    @GetMapping("/notifications/{memberId}")
    public ResponseEntity<?> notifications(@PathVariable Long memberId){
        Optional<Member> memberOptional = memberService.getMember(memberId);

        if ( memberOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("memberId not found");
        }

        List<Reservation> reservations = reservationService.returnPendingNotifications(memberOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body(reservations);
    }
}
