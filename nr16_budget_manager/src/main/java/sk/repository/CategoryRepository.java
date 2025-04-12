package sk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.model.Category;

//@Repository adnotation not necessary here (Spring recognizes JpaRepository)
//"Category" = entity type;
//"Long" = id type (type has to be specified here and in the entity separately)
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //auto-implementation
}
