package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.Plane;
import com.onkonfeton.avia.service.PlaneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plane")
public class PlaneController {

    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @GetMapping
    public String index(){
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

}
