package sk.bookings.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import sk.bookings.models.Apartment;
import sk.bookings.repositories.ApartmentRepository;

@Controller
@RequestMapping("/apartments")
public class ApartmentController {
    
    private ApartmentRepository repo; //autowired through constructor

    public ApartmentController(ApartmentRepository repo) {
        this.repo = repo;
    }
    
    //Create new 'Apartement' instance to use in HTML form
    //Add all apartments from the repo to the model
    @GetMapping()
    public String getAll(Model model) {
        //attribute doesn't need an explicit name like this: ("apartment", new Apartment());
        model.addAttribute(new Apartment());
        model.addAttribute("apartments", repo.findAll());
        return "apartments";
    }

    //Save the provided instance of the Apartment class in the database through the repository
    //'Apartment' instance is modified here with data from HTML form
    //'Apartment' instance is saved to the database in the end
    //@Valid & 'errors' = required for Bean Validation to work (inside Apartment class)
    @PostMapping()
    public String createTable(@Valid Apartment apartment, Errors errors, Model model) {
        //if errors, show client the same view as with GET method
        //when no errors = apartment entity is valid, save to the db
        if (errors.hasErrors()) {
            model.addAttribute("apartments", repo.findAll()); //TODO: is this call nessesary? is this not already in model?
            return "apartments";
        }
        else {
            repo.save(apartment);
            return "redirect:/apartments"; //to refresh
        }
    }
}
