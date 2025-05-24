package sk.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) //@Column = specifying column restrictions
    private String name;

    @ManyToMany(mappedBy = "genres") //@ManyToMany *needs* a join table; "mappedBy" means that relation is mapped elsewhere (-> Book class)
    private Set<Book> books;

    //*** contructors ***
    
    public Genre() {}

    public Genre(String name) {
        this.name = name;
    }

    //*** getters & setters ***

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
