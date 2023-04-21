package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.Flight;
import com.onkonfeton.avia.model.Person;
import com.onkonfeton.avia.service.CityService;
import com.onkonfeton.avia.service.FlightService;
import com.onkonfeton.avia.service.PlaneService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    @GetMapping("/new")
    public String getNewFlightForm(@ModelAttribute("flight") Flight flight, Model model){
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("planes", planeService.findAll());
        model.addAttribute("minTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm")));
        model.addAttribute("maxTime", LocalDateTime.now().plusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm")));
        return "flight/new";
    }

    @PostMapping("")
    public String addNewFlight(@ModelAttribute("flight") Flight flight){
        flightService.save(flight);
        return "redirect:/";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("flight") Flight flight){
        flightService.update(flight);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String showFlight(@PathVariable("id") int id,
                             Model model){
        model.addAttribute("flight", flightService.findById(id));
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("planes", planeService.findAll());
        return "flight/edit";
    }

    @DeleteMapping("/{id}")
    public String deleteFlight(@PathVariable("id") int id){
        flightService.delete(id);
        return "redirect:/";

    }
}
