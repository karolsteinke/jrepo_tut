package sk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sk.dto.TransactionDto;
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

    //def.: add all transactions to the model
    //def.: return 'list' view (showing all transactions)
    @GetMapping("/transactions") //ENDPOINT
    public String listTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAll();
        model.addAttribute("transactions", transactions);
        return "transactions/list";
    }

    //def.: add dto & categories to the model, to use in HTML form;
    //def.: return 'form' view (for user input)
    @GetMapping("/transactions/form") //ENDPOINT
    public String showForm(Model model)  {
        model.addAttribute("transactionDto", new TransactionDto());
        addCategoriesToModel(model);       
        return "transactions/form";
    }

    //def.: call service to save received DTO (user input from HTML form)
    //def.: redirect to 'form' view
    @PostMapping("/transactions/save") //ENDPOINT
    public String saveTransaction(@ModelAttribute @Valid TransactionDto TransactionDto, BindingResult result, Model model) {
       
        //if errors -> build model (analog to GET) and return 'form' view
        if (result.hasErrors()) {
            addCategoriesToModel(model); //spring *auto-adds* received 'transactionDto' to the model (with its errors) but categories have to be added explicitly
            return "transactions/form";
        }
        transactionService.saveTransaction(TransactionDto);
        return "redirect:/transactions/form"; //tip: after POST use "redirect" to force new *GET* request (otherwise browser would use last request = POST)
    }

    //def.: add categories to the model (helper class)
    private void addCategoriesToModel(Model model) {
        model.addAttribute("incomeCategories", categoryRepository.findByType(TransactionType.INCOME));
        model.addAttribute("expenseCategories", categoryRepository.findByType(TransactionType.EXPENSE));
    }
}
