package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.User;
import com.onkonfeton.avia.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public String add(@ModelAttribute("person") User user){
        userRepository.save(user);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new User());
        return "people/new";
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", userRepository.findAll());
        return "people/index";
    }
}
