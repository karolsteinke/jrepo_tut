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

    //def.: add all transactions to the model, return view for this data ("list")
    @GetMapping("/transactions")
    public String listTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAll();
        model.addAttribute("transactions", transactions);
        return "transactions/list";
    }

    //def.: add dtos to the model, to use in HTML forms; return view with forms
    //def.: add categories to the model (matching 'type')
    @GetMapping("/transactions/form")
    public String showForm(Model model)  {
        model.addAttribute("incomeDto", new TransactionDto(TransactionType.INCOME));
        model.addAttribute("expenseDto", new TransactionDto(TransactionType.EXPENSE));
        model.addAttribute("incomeCategories", categoryRepository.findByType(TransactionType.INCOME));
        model.addAttribute("expenseCategories", categoryRepository.findByType(TransactionType.EXPENSE));
        
        return "transactions/form";
    }

    //def.: call service to save dto (user input from HTML form)
    //tip: after POST use "redirect" to force new *GET* request (otherwise browser would use last request = POST)
    @PostMapping("/transactions/save")
    public String saveTransaction(@ModelAttribute @Valid TransactionDto dto, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            //
        }
        
        transactionService.saveTransaction(dto);
        return "redirect:/transactions/form";
    }
}
