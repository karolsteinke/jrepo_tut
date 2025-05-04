package sk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.model.Book;

//@Repository adnotation not necessary (Spring recognizes JpaRepository)
//"Book" = entity type; "Long" = id type
public interface BookRepository extends JpaRepository<Book, Long> {
    //auto-implementation: save(), findaAll(), findById()...
}
