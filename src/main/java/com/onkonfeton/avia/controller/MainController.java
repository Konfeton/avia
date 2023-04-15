package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.Ticket;
import com.onkonfeton.avia.service.FlightService;
import com.onkonfeton.avia.service.PersonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final FlightService flightService;
    private final PersonService personService;

    public MainController(FlightService flightService, PersonService personService) {
        this.flightService = flightService;
        this.personService = personService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session){
        model.addAttribute("flights", flightService.findAll());
        model.addAttribute("ticket", new Ticket());
        session.setAttribute("user", personService.findById(100));
        return "index.html";
    }
}
