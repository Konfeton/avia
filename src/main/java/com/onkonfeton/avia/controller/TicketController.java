package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.Flight;
import com.onkonfeton.avia.model.Person;
import com.onkonfeton.avia.model.Ticket;
import com.onkonfeton.avia.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("tickets", ticketService.findAll());
        return "ticket/index";
    }

    @GetMapping("/new")
    public String getNewTicketForm(@ModelAttribute("ticket") Ticket ticket, Model model){
        return "ticket/new";
    }

    @PostMapping("/{flight_id}")
    public String addNewTicket(@PathVariable("flight_id") int flight_id, HttpSession session){
        Ticket ticket = new Ticket();
        ticket.setTime(LocalDateTime.now());
        ticketService.save(ticket, flight_id, ((Person)session.getAttribute("user")).getId());
        return "redirect:/";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("ticket") Ticket ticket){
        ticketService.update(ticket);
        return "redirect:/ticket";
    }

    @GetMapping("/{id}/edit")
    public String showTicket(@PathVariable("id") int id,
                             Model model){
        model.addAttribute("ticket", ticketService.findById(id));
        return "ticket/edit";
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable("id") int id){
        ticketService.delete(id);
        return "redirect:/ticket";

    }
}
