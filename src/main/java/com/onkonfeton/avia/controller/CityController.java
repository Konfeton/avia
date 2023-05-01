package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.City;
import com.onkonfeton.avia.service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public String index(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                        Model model){
        if (!name.equals("")){
            model.addAttribute("cities", cityService.findByName(name));
        }else {
            model.addAttribute("cities", cityService.findAll());
        }
        return "city/index";
    }

    @GetMapping("/new")
    public String newPlaneForm(Model model){
        model.addAttribute("city", new City());
        return "city/new";
    }

    @PostMapping
    public String newPlane(@ModelAttribute("city") City city){
        cityService.save(city);
        return "redirect:/city";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") int id, Model model){
        model.addAttribute("city", cityService.findById(id));
        return "city/edit";
    }

    @PatchMapping("/{id}")
    public String saveChanges(@ModelAttribute("city") City city){
        cityService.update(city);
        return "redirect:/city";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        cityService.delete(id);
        return "redirect:/city";
    }

}
