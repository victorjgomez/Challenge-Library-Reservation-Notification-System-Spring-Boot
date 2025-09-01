package com.library.library.borrow;


import com.library.library.book.Book;
import com.library.library.book.BookService;
import com.library.library.member.Member;
import com.library.library.member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BorrowResource {

    private BorrowService borrowService;
    private BookService bookService;

    private MemberService memberService;

    public BorrowResource(BorrowService borrowService, BookService bookService, MemberService memberService) {
        this.borrowService = borrowService;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @PostMapping("/borrow")
    public ResponseEntity<?> createBorrow(@RequestParam Long memberId, @RequestParam String isbn) {
        Optional<Book> bookOptional = bookService.getBook(isbn);
        Optional<Member> memberOptional = memberService.getMember(memberId);

        if (bookOptional.isEmpty() || memberOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("memberId or isbn not found");
        }

        Borrow borrow = new Borrow(bookOptional.get(), memberOptional.get());
        Borrow createdBorrow = this.borrowService.createBorrow(borrow);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBorrow);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnBorrow(@RequestParam Long memberId, @RequestParam String isbn){
        Optional<Book> bookOptional = bookService.getBook(isbn);
        Optional<Member> memberOptional = memberService.getMember(memberId);

        if (bookOptional.isEmpty() || memberOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("memberId or isbn not found");
        }

        Optional<Borrow> borrowOptional = borrowService.getByBookAndMember(bookOptional.get(), memberOptional.get());

        if(borrowOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("borrow not found");
        }

        Borrow returnBorrow = borrowService.returnBook(borrowOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body(returnBorrow);
    }
}
