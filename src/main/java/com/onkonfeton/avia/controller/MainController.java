package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.Ticket;
import com.onkonfeton.avia.service.FlightService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final FlightService flightService;

    public MainController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session){
        model.addAttribute("flights", flightService.findAll());
        model.addAttribute("ticket", new Ticket());
        return "index";
    }
}
