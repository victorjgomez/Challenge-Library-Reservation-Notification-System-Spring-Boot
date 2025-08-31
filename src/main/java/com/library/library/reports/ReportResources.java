package com.library.library.reports;


import com.library.library.book.Book;
import com.library.library.borrow.BorrowService;
import com.library.library.member.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportResources {

    private BorrowService borrowService;

    @GetMapping("/reports/overdue")
    public List<Member> overuse(){
        return borrowService.getAllMemberWithOverDue();
    }

    @GetMapping("/reports/top-books")
    public List<Book> topBook(@PathVariable int limit){
        throw new UnsupportedOperationException();
    }
}
