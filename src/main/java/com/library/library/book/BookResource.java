package com.library.library.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookResource {

    private BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book){
        if (book == null) {
            throw new IllegalArgumentException("Book data must not be null");
        }
        return bookService.createBook(book);
    }

    @GetMapping("/books")
    public List<Book> listBooks(){
        return this.bookService.listOfBook();
    }
}
