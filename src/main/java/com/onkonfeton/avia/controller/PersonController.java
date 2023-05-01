package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.exceptions.PersonAlreadyExistException;
import com.onkonfeton.avia.model.Person;
import com.onkonfeton.avia.model.enums.Role;
import com.onkonfeton.avia.model.enums.Status;
import com.onkonfeton.avia.security.Hash;
import com.onkonfeton.avia.service.PersonService;
import com.onkonfeton.avia.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final TicketService ticketService;

    public PersonController(PersonService personService, TicketService ticketService) {
        this.personService = personService;
        this.ticketService = ticketService;
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
        if (user != null && user.getPassword().equals(Hash.encrypt(person.getPassword())) && user.getStatus().toString().equals("ACTIVE")) {
            httpSession.setAttribute("user", user);
            return "redirect:/";
        }else{
            model.addAttribute("person", person);
            return "people/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/registration")
    public String newPersonForm(Model model){
        if (!model.containsAttribute("person")) {
            model.addAttribute("person", new Person());
        }
        model.addAttribute("min", LocalDate.now().minusYears(80));
        model.addAttribute("max", LocalDate.now().minusYears(18));
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
        model.addAttribute("roles", Role.values());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("min", LocalDate.now().minusYears(80));
        model.addAttribute("max", LocalDate.now().minusYears(18));

        model.addAttribute("tickets", ticketService.findByPerson(personService.findById(id)));

        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id){
        try {
            personService.update(person, id);
        } catch (PersonAlreadyExistException e) {
        }
        return "redirect:/people";
    }

    @GetMapping
    public String index(@RequestParam(value = "min", required = false)LocalDate min,
                        @RequestParam(value = "max", required = false)LocalDate max,
                        @RequestParam(value = "param", required = false, defaultValue = "null")String param,
                        @RequestParam(value = "name", required = false)String name,
                        Model model){
        List<Person> people = null;
        if (min != null && max != null) {
            people = personService.findByDateOfBirthdayBetween(min, max);
        }
        if (!param.equals("null")){
            switch (param) {
                case "firstName" -> people = personService.findByFirstName(name);
                case "lastName" -> people = personService.findByLastName(name);
            }
        }
        if (min == null && max == null && param.equals("null")){
            people = personService.findAll();
            model.addAttribute("people", people);
        }else {
            model.addAttribute("people", people);
        }
        if (people !=null && people.size() != 0) {
            model.addAttribute("avgYears", people.stream().map(Person::years).mapToInt(Integer::intValue).sum() / people.size());
        }else {
            model.addAttribute("avgYears", 0);
        }
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

    @GetMapping("/{id}/ticket")
    public String getTickets(@PathVariable int id,
                             Model model){
        model.addAttribute("tickets", ticketService.findByPerson(personService.findById(id)));
        return "people/ticket";
    }
}
