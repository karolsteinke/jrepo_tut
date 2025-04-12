package sk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sk.dto.TransactionDto;
import sk.model.Transaction;
import sk.service.TransactionService;

@Controller
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;

    //def.: add all transactions to the model, return view for this data ("list")
    @GetMapping("/transactions")
    public String listTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAll();
        model.addAttribute("transactions", transactions);
        return "transactions/list";
    }

    @GetMapping("/form")
    public String inputTransaction(Model model)  {
        model.addAttribute("transactionDto", new TransactionDto());
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
