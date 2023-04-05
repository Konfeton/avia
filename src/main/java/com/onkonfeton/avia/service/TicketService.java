package com.onkonfeton.avia.service;

import com.onkonfeton.avia.model.Ticket;
import com.onkonfeton.avia.repository.TicketRepository;
import com.onkonfeton.avia.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    public Ticket save(Ticket person) {
        return ticketRepository.save(person);
    }

    public Ticket findById(int id){
        return ticketRepository.findById(id).stream().findAny().orElse(null);
    }

    public void update(Ticket person){
        ticketRepository.save(person);
    }

    public void delete(int id) {
        ticketRepository.deleteById(id);
    }
}
