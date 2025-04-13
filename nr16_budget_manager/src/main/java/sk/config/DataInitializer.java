package sk.config;

import org.springframework.stereotype.Component;

import java.util.List;
import jakarta.annotation.PostConstruct;
import sk.model.Category;
import sk.model.TransactionType;
import sk.repository.CategoryRepository;

@Component
public class DataInitializer {
    
    private final CategoryRepository categoryRepository;

    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //def.: add hard-coded categories to the db
    //@PostConstruct = method will auto-run just after bean creation and DI
    @PostConstruct
    public void init() {
        if (categoryRepository.count() == 0) {
            categoryRepository.saveAll(List.of(
                new Category("RENT OR MORTAGE", TransactionType.EXPENSE),
                new Category("GROCERIES", TransactionType.EXPENSE),
                new Category("ENTERTAINMENT", TransactionType.EXPENSE),
                new Category("PERSONAL CARE", TransactionType.EXPENSE),
                new Category("SALARY", TransactionType.INCOME),
                new Category("FREELANCE CONTRACT", TransactionType.INCOME)
            ));
        }
    }
}
