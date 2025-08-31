package com.library.library.book;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> getBook(String isbn){
        return bookRepository.findById(isbn);
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> listOfBook(){
        return this.bookRepository.findAll();
    }

    public synchronized Book decreaseStock(Book book){
        if (book.getStock() < 0){
            throw new RuntimeException("There is not stock available for this book");
        }

       book.setStock(book.getStock() - 1);

       return this.bookRepository.save(book);
    }

    public synchronized Book increaseStock(Book book){
        book.setStock(book.getStock() + 1);

        return this.bookRepository.save(book);
    }


}
