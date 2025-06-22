package sk.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    //Return filtered and sorted books from the db
    public List<Book> getAllBooksWithFilters(String username, String author, List<Long> genreIds, String sortBy, String order) {
        List<Book> books = bookRepository.findAllWithRatings();

        //Filter only matching selected author
        if (author != null && !author.isBlank()) {
            books = books.stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
        }

        //Filter only matching selected genres (AND)
        if (genreIds != null && !genreIds.isEmpty()) {
            books = books.stream()
                .filter(book -> {
                    Set<Long> bookGenreIds = book.getGenres().stream()
                        .map(Genre::getId)
                        .collect(Collectors.toSet());
                    return bookGenreIds.containsAll(genreIds);
                })
                .collect(Collectors.toList());
        }

        //Sorting = to do...

        //Set books userRating to *currently logged* user's rating
        for (Book b : books) {
            b.getRatings().stream()
                .filter(r -> r.getUser().getUsername().equals(username))
                .findFirst() //returns Optional<T>
                .ifPresent(r -> b.setUserRating(r.getRatingValue()));
        }
        
        return books;
    }

    //Return all books from the db; for each book set 'userRating' to value matching logged user
    // public List<Book> getAllBooks(String username) {
    //     List<Book> books = bookRepository.findAllWithRatings();

    //     //Set book's user rating to *currently logged* user
    //     for (Book b : books) {
    //         b.getRatings().stream()
    //             .filter(r -> r.getUser().getUsername().equals(username))
    //             .findFirst() //returns Optional<T>
    //             .ifPresent(r -> b.setUserRating(r.getRatingValue()));
    //     }
        
    //     return books;
    // }

    //Return all genres from the db, converted to Dtos
    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll().stream()
            .map(g -> new GenreDto(g.getId(), g.getName()))
            .toList();
    }

    //Convert Dto->Book & save entity to the db
    public boolean saveBook(BookDto dto) {
        //Check if not already exists
        boolean alreadyExists = bookRepository.findByTitleAndAuthorAndPublicationYear(dto.getTitle(), dto.getAuthor(), dto.getPublicationYear()).isPresent();
        if (alreadyExists) return false;
        
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
        return true;
    }

    //Create new rating, set its relation to the book, and save it to the db
    public void addRating(Long bookId, int ratingValue, String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow( () -> new RuntimeException("User not found"))
        ;
        Book book = bookRepository.findById(bookId)
            .orElseThrow( () -> new RuntimeException("Book not found"))
        ;
        
        //Check if not already rated by logged user
        boolean alreadyRated = ratingRepository.findByUserAndBook(user, book).isPresent();
        if (alreadyRated) return;
        
        Rating rating = new Rating();
        rating.setBook(book);
        rating.setRatingValue(ratingValue);
        rating.setUser(user);
        ratingRepository.save(rating);
    }
}
