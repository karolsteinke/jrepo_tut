package sk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sk.dto.TransactionDto;
import sk.model.Transaction;
import sk.repository.CategoryRepository;
import sk.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    //def.: constructor; DI for interface fields with
    public TransactionService (TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }
    
    //def.: convert DTO -> Transaction entity; save entity to the db
    public void saveTransaction(TransactionDto dto) {
        Transaction tx = new Transaction();
        tx.setAmount(dto.getAmount());
        tx.setDate(dto.getDate());
        tx.setType(dto.getType());
        tx.setDescription(dto.getDescription());
        tx.setCategory(categoryRepository.findById(dto.getCategoryId()).orElseThrow());
        transactionRepository.save(tx);
    }

    //def.: return all transactions from the db
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public void findByMonthYear(int month, int year) {

    }

    public void delete(Long id) {

    }
    
}
