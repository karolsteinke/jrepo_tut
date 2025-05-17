package sk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sk.model.Book;

import java.util.List;

//@Repository adnotation not necessary (Spring recognizes JpaRepository)
//"Book" = entity type; "Long" = id type
public interface BookRepository extends JpaRepository<Book, Long> {
    
    //auto-implementation: save(), findaAll(), findById()...

    //Return list of all books *loaded with ratings*
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.ratings") //select all book and ratings (force ratings *to load*)
    List<Book> findAllWithRatings();
}
