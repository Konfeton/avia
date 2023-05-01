package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.Airline;
import com.onkonfeton.avia.service.AirlineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/airline")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    public String index(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                        Model model){
        if (!name.equals("")){
            model.addAttribute("airlines", airlineService.findByName(name));
        }else {
            model.addAttribute("airlines", airlineService.findAll());
        }
        return "airline/index";
    }

    @GetMapping("/new")
    public String newAirlineForm(Model model){
        model.addAttribute("airline", new Airline());
        return "airline/new";
    }

    @PostMapping
    public String newAirline(@ModelAttribute("airline") Airline airline){
        airlineService.save(airline);
        return "redirect:/airline";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") int id, Model model){
        model.addAttribute("airline", airlineService.findById(id));
        return "airline/edit";
    }

    @PatchMapping("/{id}")
    public String saveChanges(@ModelAttribute("airline") Airline airline){
        airlineService.update(airline);
        return "redirect:/airline";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        airlineService.delete(id);
        return "redirect:/airline";
    }

}
