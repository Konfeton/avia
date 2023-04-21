package com.onkonfeton.avia.controller;

import com.onkonfeton.avia.model.Person;
import com.onkonfeton.avia.model.Ticket;
import com.onkonfeton.avia.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("")
    public String addNewTicket(@RequestParam("flight_id") int flight_id, HttpSession session){
        ticketService.save(new Ticket(), flight_id, ((Person)session.getAttribute("user")).getId());
        return "redirect:/";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("ticket") Ticket ticket){
        ticketService.update(ticket);
        return "redirect:/ticket";
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable("id") int id){
        ticketService.delete(id);
        return "redirect:/ticket";

    }
}
