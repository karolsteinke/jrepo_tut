package sk.dto;

import java.util.Set;

import jakarta.validation.constraints.NotNull;
import sk.model.Genre;

public class BookDto {
    
    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Author is required")
    private String author;

    private int publicationYear;

    private Set<Genre> genres;

    //constructors
    public BookDto() {} //Spring uses parameterless constructor in POST to create Dto (hidden)

    //getters & setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
}
