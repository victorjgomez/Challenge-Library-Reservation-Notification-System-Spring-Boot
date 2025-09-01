package com.library.library.reports;


import com.library.library.book.Book;
import com.library.library.borrow.BorrowService;
import com.library.library.member.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportResources {

    private final BorrowService borrowService;

    public ReportResources(BorrowService borrowService){
        this.borrowService = borrowService;
    }

    @GetMapping("/reports/overdue")
    public List<Member> overuse(){
        return borrowService.getAllMemberWithOverDue();
    }

    @GetMapping("/reports/top-books")
    public List<Book> topBook(@RequestParam(defaultValue = "5") int limit){
        return borrowService.getTopBorrowedBooks(limit);
    }
}
