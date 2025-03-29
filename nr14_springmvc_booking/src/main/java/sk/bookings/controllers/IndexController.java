package sk.bookings.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    //URI with param example: http://localhost:8080/?name=John
    //just example controller, no role in booking service
    @RequestMapping("/")
    public String index(
        @RequestParam(required = false) String name,
        Model model
    ) {
        if (name == null) name = "Anonymous";
        model.addAttribute("name", name);

        return "index.html";
    }
    
}
