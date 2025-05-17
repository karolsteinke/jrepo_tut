package sk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.model.Rating;

//@Repository adnotation not necessary (Spring recognizes JpaRepository)
//"Rating" = entity type; "Long" = id type
public interface RatingRepository extends JpaRepository<Rating, Long> {
    //auto-implementation: save(), findaAll(), findById()...
}