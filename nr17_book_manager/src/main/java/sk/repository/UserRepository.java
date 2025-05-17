package sk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.model.User;

//@Repository adnotation not necessary (Spring recognizes JpaRepository)
//"User" = entity type; "Long" = id type
public interface UserRepository extends JpaRepository<User, Long> {
    
    //auto-implementation: save(), findaAll(), findById()...

    Optional<User> findByUsername(String username); //Optional<T> = wrapper for <T>, can be T or null, *needs* explicit def. what happens when null (= protects againts NullPointerException)
}