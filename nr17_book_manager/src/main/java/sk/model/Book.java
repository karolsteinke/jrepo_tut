package sk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) //@Column = specifying column restrictions
    private String title;

    @Column(nullable = false) //@Column = specifying column restrictions
    private String author;

    private int publicationYear;

    //@ManyToMany needs a join table to understand entities relation
    //@JoinTable creates a join table, to connect book to genre
    @ManyToMany
    @JoinTable(
        name = "book_genre", //join table name
        joinColumns = @JoinColumn(name = "book_id"), //key to book entity
        inverseJoinColumns = @JoinColumn(name = "genre_id") //key to genre entity
    )
    private Set<Genre> genres;

    // Optionally: @ManyToOne private User addedBy;

    //constructors
    public Book() {};

    public Book(String title, String author, int publicationYear, Set<Genre> genres) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genres = genres;
    }

    //getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
