package sk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.model.Todo;

//@Repository adnotation not necessary here (Spring recognizes JpaRepository)
//JpaRepository extends CrudRepository (more functionality)
//"Todo"-entity type;
//"Long"-id type (type has to be specified here and in the entity separately)
public interface TodoRepository extends JpaRepository<Todo, Long> {
    //auto-implementation
}
