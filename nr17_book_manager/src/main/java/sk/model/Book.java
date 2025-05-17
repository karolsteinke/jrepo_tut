package sk.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) //@Column = specifying column restrictions
    private String title;

    @Column(nullable = false)
    private String author;

    private int publicationYear;

    @ManyToMany //@ManyToMany *needs* a join table; @JoinTable creates it
    @JoinTable(
        name = "book_genre", //join table name
        joinColumns = @JoinColumn(name = "book_id"), //key to 'book' entity
        inverseJoinColumns = @JoinColumn(name = "genre_id") //key to 'genre' entity
    )
    private Set<Genre> genres;

    @ManyToOne
    private User addedBy;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL) //"mappedBy" means that relation is mapped elsewhere (-> Rating class); "cascade" means that deleting a book = deleting related ratings
    private List<Rating> ratings = new ArrayList<>(); //FetchType.LAZY (default) will cause problem. So, force ratings *to load* with query in bookRepository.

    //constructors
    public Book() {};

    public Book(String title, String author, int publicationYear, Set<Genre> genres) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genres = genres;
    }

    //methods
    //Return average for all related ratings
    @Transient //@Transient = don't map, because field is dynamically calculated; optional here, because 'averageRating' doesn't exist anyway
    public double getAverageRating() {
        try {
            if (ratings == null || ratings.isEmpty()) {
                return 0.0;
            }
            return ratings.stream()
                .mapToInt(Rating::getRatingValue)
                .average()
                .orElse(0.0)
            ;
        } catch (org.hibernate.LazyInitializationException e) { //in case of yet not initialized ratings list (lazy)
            return 0.0;
        }
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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }    
}
