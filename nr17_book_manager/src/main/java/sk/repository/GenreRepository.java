package sk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.model.Genre;

//@Repository adnotation not necessary (Spring recognizes JpaRepository)
//"Genre" = entity type; "Long" = id type
public interface GenreRepository extends JpaRepository<Genre, Long> {
    //auto-implementation: save(), findaAll(), findById()...
}