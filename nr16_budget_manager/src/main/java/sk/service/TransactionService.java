package sk.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import sk.dto.TransactionDto;
import sk.model.Transaction;
import sk.repository.CategoryRepository;
import sk.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    //def.: constructor, DI for *interface-type* fields
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

    //def.: return filtered and sorted transactions from the db
    public List<Transaction> findAllWithFilters(String type, Optional<Long> categoryId, String sortBy, String order) {
        List<Transaction> transactions = transactionRepository.findAll();

        //Filtering
        if (type != null && !type.isBlank()) {
            transactions = transactions.stream()
                .filter(t -> t.getType().name().equalsIgnoreCase(type)) //only matching type
                .collect(Collectors.toList());
        }

        if (categoryId.isPresent()) {
            transactions = transactions.stream()
                .filter(t -> t.getCategory().getId().equals(categoryId.get())) //only matching category
                .collect(Collectors.toList());
        }

        //Sorting
        Comparator<Transaction> comparator;

        switch (sortBy) {
            case "amount":
                comparator = Comparator.comparing(Transaction::getAmount);
                break;
            case "category":
                comparator = Comparator.comparing(t -> t.getCategory().getName()); //using lambda exp. because we didn't define method which does that
                break;
            case "type":
                comparator = Comparator.comparing(Transaction::getType);
                break;
            case "date":
            default:
                comparator = Comparator.comparing(Transaction::getDate);
        }

        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        transactions.sort(comparator);

        //filtered and sorted result
        return transactions;
    }

    public void findByMonthYear(int month, int year) {
        //work in progress...
    }

    public void delete(Long id) {
        //work in progress...
    }
    
}
