package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.Flight;
import com.onkonfeton.avia.model.Person;
import com.onkonfeton.avia.service.CityService;
import com.onkonfeton.avia.service.FlightService;
import com.onkonfeton.avia.service.PlaneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;
    private final CityService cityService;
    private final PlaneService planeService;

    public FlightController(FlightService flightService, CityService cityService, PlaneService planeService) {
        this.flightService = flightService;
        this.cityService = cityService;
        this.planeService = planeService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("flights", flightService.findAll());
        return "flight/index";
    }



    @GetMapping("/new")
    public String getNewFlightForm(@ModelAttribute("flight") Flight flight, Model model){
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("planes", planeService.findAll());
        return "flight/new";
    }

    @PostMapping
    public String addNewFlight(@ModelAttribute("flight") Flight flight){
        System.out.println(flight.toString());
        flightService.save(flight);
        return "redirect:/flight";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("flight") Flight flight){
        flightService.update(flight);
        return "redirect:/flight";
    }

    @GetMapping("/{id}/edit")
    public String showPerson(@PathVariable("id") int id,
                             Model model){
        model.addAttribute("flight", flightService.findById(id));
        return "flight/edit";
    }

}
