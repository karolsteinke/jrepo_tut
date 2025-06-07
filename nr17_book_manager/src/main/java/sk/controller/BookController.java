package sk.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import sk.dto.BookDto;
import sk.model.Book;
import sk.repository.GenreRepository;
import sk.service.BookService;

@Controller
public class BookController {
    
    private final BookService bookService;
    
    public BookController(BookService bookService, GenreRepository genreRepository) {
        this.bookService = bookService;
    }

    //Add all books to the model; Return 'list' view, showing all books
    @GetMapping("/book-list")
    public String listBooks(
        @RequestParam(required = false) String author,
        @RequestParam(required = false) List<Long> genreIds,
        @RequestParam(required = false, defaultValue = "title") String sortBy,
        @RequestParam(required = false, defaultValue = "desc") String order,
        Model model, 
        Principal principal
    ) {
        List<Book> books = bookService.getAllBooksWithFilters(principal.getName(), author, genreIds, sortBy, order); //principal = logged user
        
        model.addAttribute("books", books);
        model.addAttribute("allGenres", bookService.getAllGenres());
        return "book-list";
    }

    //Add book & all genres (dtos) to the model; Return 'form' view, for user input
    @GetMapping("/book-form")
    public String showBookForm(Model model) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("allGenres", bookService.getAllGenres());
        return "book-form";
    }

    //Save received BookDto (user input) to the db; Redirect to 'form' view
    @PostMapping("/book-form/save")
    public String saveBook(@ModelAttribute @Valid BookDto bookDto, BindingResult result, Model model) { //BindingResult is for errors
        
        //1. Check for Dto validation erros
        if (result.hasErrors()) {
            model.addAttribute("allGenres", bookService.getAllGenres()); //add all attiubutes which book-form uses again
            return "book-form"; //render view again (now showing errors)
        }
        //2. Try to save the book, check if success or error
        boolean success = bookService.saveBook(bookDto);
        if (!success) {
            model.addAttribute("allGenres", bookService.getAllGenres());
            model.addAttribute("duplicateError", "This book already exists.");
            return "book-form"; //render view again (now showing errors)
        }

        return "redirect:/book-form";
    }

    //Add a rating of "value" to book with "id"; Redirect to new 'list' view
    @PostMapping("/book/{id}/rate")
    public String rateBook(@PathVariable Long id, @RequestParam int ratingValue, Principal principal) {
        bookService.addRating(id, ratingValue, principal.getName()); //principal = logged user
        return "redirect:/book-list";
    }
}
