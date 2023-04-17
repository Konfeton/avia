package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.exceptions.PersonAlreadyExistException;
import com.onkonfeton.avia.model.Person;
import com.onkonfeton.avia.service.PersonService;
import jakarta.servlet.http.HttpSession;
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
    public String add(@ModelAttribute("person") Person person,
                      HttpSession httpSession,
                      Model model){
        try {
            personService.save(person);
            httpSession.setAttribute("user", person);
            return "redirect:/";
        } catch (PersonAlreadyExistException e) {
            model.addAttribute("person", person);
            return "people/registration";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("person") Person person,
                        HttpSession httpSession,
                        Model model){
        Person user = personService.findByEmail(person.getEmail());
        if (user != null && user.getPassword().equals(person.getPassword()) && user.getStatus().toString().equals("ACTIVE")) {
            httpSession.setAttribute("user", user);
            return "redirect:/";
        }else{
            model.addAttribute("person", person);
            return "people/login";
        }
    }

    @GetMapping("/registration")
    public String newPersonForm(Model model){
        if (!model.containsAttribute("person")) {
            model.addAttribute("person", new Person());
        }
        return "people/registration";
    }

    @GetMapping("/login")
    public String logInForm(Model model){
        if (!model.containsAttribute("person")) {
            model.addAttribute("person", new Person());
        }
        return "people/login";
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
