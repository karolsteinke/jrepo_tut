package sk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sk.dto.TransactionDto;
import sk.model.Category;
import sk.model.Transaction;
import sk.model.TransactionType;
import sk.repository.CategoryRepository;
import sk.service.TransactionService;

@Controller
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CategoryRepository categoryRepository;

    //def.: add all transactions to the model, return view for this data ("list")
    @GetMapping("/transactions")
    public String listTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAll();
        model.addAttribute("transactions", transactions);
        return "transactions/list";
    }

    //def.: add TransactionDto to the model, to use in HTML form; return view for this form
    //def.: add categories to the model (all matching 'type')
    @GetMapping("/transactions/add")
    public String showForm(@RequestParam TransactionType type, Model model)  {
        List<Category> categories = categoryRepository.findByType(type);
        
        model.addAttribute("transactionDto", new TransactionDto());
        model.addAttribute("categories", categories);
        model.addAttribute("type", type);
        
        return "transactions/form";
    }

    //def.: call service to save TransactionDto (user input from HTML form)
    //tip: after POST use "redirect" to force new GET request (to block user from calling POST again with duplicated form)
    @PostMapping("/transactions/add")
    public String addTransaction(@ModelAttribute TransactionDto transactionDto) {
        transactionService.saveTransaction(transactionDto);
        return "redirect:/transactions";
    }
}
