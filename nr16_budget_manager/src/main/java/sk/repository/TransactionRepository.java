package sk.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.model.Transaction;

//@Repository adnotation not necessary here (Spring recognizes JpaRepository)
//"Transaction" = entity type;
//"Long" = id type (type has to be specified here and in the entity separately)
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    //auto-implementation

    List<Transaction> findByDateBetween(LocalDate start, LocalDate end); //auto-implementation
}
