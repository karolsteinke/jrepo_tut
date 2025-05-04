package sk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sk.model.Book;
import sk.repository.BookRepository;

@Service
public class BookService {
    
    private final BookRepository bookRepository;

    //constructor, DI for *interface-type* fields
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //return all books from the db
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
