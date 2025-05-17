package sk.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import sk.dto.BookDto;
import sk.dto.GenreDto;
import sk.model.Book;
import sk.model.Genre;
import sk.model.Rating;
import sk.model.User;
import sk.repository.BookRepository;
import sk.repository.GenreRepository;
import sk.repository.RatingRepository;
import sk.repository.UserRepository;

@Service
public class BookService {
    
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;

    //Constructor, DI for *interface-type* fields
    public BookService(BookRepository bookRepository, GenreRepository genreRepository, RatingRepository ratingRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    //Return all books from the db
    public List<Book> getAllBooks() {
        return bookRepository.findAllWithRatings();
    }

    //Return all genres from the db, converted to Dtos
    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll().stream()
            .map(g -> new GenreDto(g.getId(), g.getName()))
            .toList();
    }

    //Convert Dto->Book & save entity to the db
    public void saveBook(BookDto dto) {
        Book book = new Book();
        List<Genre> genres = genreRepository.findAllById(dto.getGenreIds());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublicationYear(dto.getPublicationYear());
        book.setGenres(new HashSet<>(genres)); 

        //Set Book.addedBy (by checking logged user)
        String username = SecurityContextHolder.getContext().getAuthentication().getName(); //get logged user from the context
        User user = userRepository.findByUsername(username).orElseThrow();
        book.setAddedBy(user);
        
        bookRepository.save(book);
    }

    //Create new rating, set its relation to the book, and save it to the db
    public void addRating(Long bookId, int ratingValue) {
        Book book = bookRepository
            .findById(bookId)
            .orElseThrow( () -> new RuntimeException("Book not found"))
        ;
        
        Rating rating = new Rating();
        rating.setBook(book);
        rating.setRatingValue(ratingValue);

        //Set Rating.user (by checking logged user)
        String username = SecurityContextHolder.getContext().getAuthentication().getName(); //get logged user from the context
        User user = userRepository.findByUsername(username).orElseThrow();
        rating.setUser(user);

        ratingRepository.save(rating);
    }
}
