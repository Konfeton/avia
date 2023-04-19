package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.City;
import com.onkonfeton.avia.service.CityService;
import com.onkonfeton.avia.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class MainController {

    private final FlightService flightService;
    private final CityService cityService;

    public MainController(FlightService flightService, CityService cityService) {
        this.flightService = flightService;
        this.cityService = cityService;
    }

    @GetMapping("/")
    public String index(@RequestParam(value = "departure_city", required = false)City departure,
                        @RequestParam(value = "arrival_city", required = false)City arrival,
                        @RequestParam(value = "date",required = false) LocalDate date,
                        Model model){
        if (departure != null && arrival!=null){
            model.addAttribute("flights", flightService.findByDepartureCityAndArrivalCity(departure,arrival));
        }else {
            model.addAttribute("flights", flightService.findAll());
        }
        model.addAttribute("cities", cityService.findAll());
        return "index";
    }
}
