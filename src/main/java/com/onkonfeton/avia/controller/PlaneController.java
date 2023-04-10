package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.Plane;
import com.onkonfeton.avia.service.PlaneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/plane")
public class PlaneController {

    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("planes", planeService.findAll());
        return "plane/index";
    }

    @GetMapping("/new")
    public String newPlaneForm(Model model){
        model.addAttribute("plane", new Plane());
        return "plane/new";
    }

    @PostMapping
    public String newPlane(@ModelAttribute("plane") Plane plane){
        planeService.save(plane);
        return "redirect:/plane";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") int id, Model model){
        model.addAttribute("plane", planeService.findById(id));
        return "plane/edit";
    }

    @PatchMapping("/{id}")
    public String saveChanges(@ModelAttribute("plane") Plane plane){
        planeService.save(plane);
        return "redirect:/plane";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        planeService.delete(id);
        return "redirect:/plane";
    }

}
