package sk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.model.Book;
import sk.model.Rating;
import sk.model.User;

//@Repository adnotation not necessary (Spring recognizes JpaRepository)
//"Rating" = entity type; "Long" = id type
public interface RatingRepository extends JpaRepository<Rating, Long> {
    
    //auto-implementation: save(), findaAll(), findById()...

    Optional<Rating> findByUserAndBook(User user, Book book);
}