package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.Person;
import com.onkonfeton.avia.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public String add(@ModelAttribute("person") Person person){
        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String newPersonForm(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person){
        personService.update(person);
        return "redirect:/people";
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", personService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id,
                           Model model){
        model.addAttribute("person", personService.findById(id));
        return "people/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personService.delete(id);
        return "redirect:/people";
    }
}
