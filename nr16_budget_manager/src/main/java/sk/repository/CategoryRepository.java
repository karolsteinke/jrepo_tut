package sk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import sk.model.Category;
import sk.model.TransactionType;

//@Repository adnotation not necessary here (Spring recognizes JpaRepository)
//"Category" = entity type;
//"Long" = id type (type has to be specified here and in the entity separately)
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    //auto-implementation

    List<Category> findByType(TransactionType type); //auto-implementation
}
