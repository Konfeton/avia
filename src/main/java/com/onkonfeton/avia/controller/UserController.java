package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.User;
import com.onkonfeton.avia.repository.UserRepository;
import com.onkonfeton.avia.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String add(@ModelAttribute("person") User user){
        userService.save(user);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String newPersonForm(Model model){
        model.addAttribute("person", new User());
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "/people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id){
        userService.update(user);
        return "redirect:/people/"+id+"/edit";
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", userService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id,
                           Model model){
        model.addAttribute("person", userService.findById(id));
        return "people/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        userService.delete(id);
        return "redirect:/people";
    }
}
