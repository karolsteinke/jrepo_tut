package sk.bookings.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sk.bookings.models.Booking;
import sk.bookings.models.Apartment;
import sk.bookings.repositories.ApartmentRepository;
import sk.bookings.repositories.BookingRepository;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    private BookingRepository bookingRepo;
    private ApartmentRepository apartmentRepo;

    public BookingController(BookingRepository bookingRepo, ApartmentRepository apartmentRepo) {
        this.bookingRepo = bookingRepo;
        this.apartmentRepo = apartmentRepo;
    }

    //Create new 'Booking' instance to allow new bookig through HTML form
    //Add all bookings from the repo to the model
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute(new Booking());
        model.addAttribute("bookings", bookingRepo.findAll());
        return "bookings";
    }

    //Save the provided instance of the Apartment class in the database through the repository
    //@Valid & 'errors' = required for Bean Validation to work (inside Apartment class)
    @PostMapping
    public String create(@Valid Booking booking, Errors errors, Model model) {
        String view;
        //if errors, show client the same view as with GET method
        //otherwise, check for free apartments and if there are any, save in the db
        if (errors.hasErrors()) {
            model.addAttribute("bookings", bookingRepo.findAll());
            view = "bookings";
        }
        else {
            int numGuests = booking.getNumGuests();
            LocalDate startDay = booking.getFromDate();
            LocalDate enDate = booking.getToDate();
            List<Apartment> availableApartments = apartmentRepo.getFreeApartments(numGuests, startDay, enDate);

            //if any search results, save booking in the db
            //otherwise, set proper flag in the model & show standard 'GET' view
            if(availableApartments.size() > 0) {
                booking.setApartment(availableApartments.get(0));
                bookingRepo.save(booking);
                view = "redirect:/bookings"; //to refresh
            }
            else {
                model.addAttribute("noApartmentAvailable", true);
                model.addAttribute("bookings", bookingRepo.findAll());
                view = "bookings";
            }
        }
        return view;
    }
    
}
