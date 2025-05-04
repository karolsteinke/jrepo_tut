package sk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sk.dto.BookDto;
import sk.model.Book;
import sk.repository.GenreRepository;
import sk.service.BookService;

@Controller
public class BookController {
    
    private final BookService bookService;
    private final GenreRepository genreRepository;

    public BookController(BookService bookService, GenreRepository genreRepository) {
        this.bookService = bookService;
        this.genreRepository = genreRepository;
    }

    //Add all books to the model; Return 'list' view, showing all books
    @GetMapping("/book-list")
    public String listBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "/book-list";
    }

    //Add Dto, genres to the model; Return 'form' view, for user input
    @GetMapping("/book-form")
    public String showBookForm(Model model) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("allGenres", genreRepository.findAll());
        return "/book-form";
    }

    //Save received Dto (user input) to the db; Redirect to 'form' view
    @PostMapping("/book-form/save")
    public String saveBook(@ModelAttribute @Valid BookDto bookDto, BindingResult bindingResult, Model model) { //BindingResult is for errors
        
        //work-in-progress
        
        return "redirect:/book-form";
    }
}
