package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.City;
import com.onkonfeton.avia.model.Flight;
import com.onkonfeton.avia.service.CityService;
import com.onkonfeton.avia.service.FlightService;
import com.onkonfeton.avia.service.PersonService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final FlightService flightService;
    private final CityService cityService;
    private final PersonService personService;

    public MainController(FlightService flightService, CityService cityService, PersonService personService) {
        this.flightService = flightService;
        this.cityService = cityService;
        this.personService = personService;
    }

    @GetMapping("/")
    public String index(@RequestParam(value = "departure_city", required = false)City departure,
                        @RequestParam(value = "arrival_city", required = false)City arrival,
                        @RequestParam(value = "date",required = false) LocalDate date,
                        @RequestParam(value = "min",required = false, defaultValue = "0") double min,
                        @RequestParam(value = "max",required = false, defaultValue = "10000") double max,
                        @RequestParam(value = "sort", required = false) String sort,
                        Model model){
        if (departure != null && arrival!=null){
            List<Flight> flights = flightService.findByCitiesAndBetweenPrice(
                    departure,
                    arrival,
                    min,
                    max,
                    Sort.by(Sort.Direction.fromString(sort), "price"));

            model.addAttribute("flights", flights
                    .stream()
                    .filter(fl -> fl.getDepartureTime().toLocalDate().isEqual(date))
                    .collect(Collectors.toList()));
        }else {
//            model.addAttribute("flights", flightService.findByDepartureTime(LocalDateTime.now()));
            model.addAttribute("flights", flightService.findAll());
        }
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("time", LocalDate.now());

        return "index";
    }
}
