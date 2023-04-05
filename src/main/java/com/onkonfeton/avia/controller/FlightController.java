package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.Flight;
import com.onkonfeton.avia.service.FlightService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("flights", flightService.findAll());
        return "flight/index";
    }



    @GetMapping("/new")
    public String getNewFlightForm(Model model){
        model.addAttribute("flight", new Flight());
        return "flight/new";
    }

    @PostMapping
    public String addNewFlight(@ModelAttribute("flight") Flight flight){
        System.out.println(flight.toString());
        flightService.save(flight);
        return "redirect:/flight/index";
    }


}
