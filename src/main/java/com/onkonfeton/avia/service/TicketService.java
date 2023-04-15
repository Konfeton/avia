package com.onkonfeton.avia.service;

import com.onkonfeton.avia.model.Ticket;
import com.onkonfeton.avia.repository.FlightRepository;
import com.onkonfeton.avia.repository.PersonRepository;
import com.onkonfeton.avia.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final PersonRepository personRepository;

    public TicketService(TicketRepository ticketRepository, FlightRepository flightRepository, PersonRepository personRepository) {
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
        this.personRepository = personRepository;
    }

    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    public Ticket save(Ticket ticket, int flight_id, int person_id) {
        ticket.setFlight(flightRepository.findById(flight_id).orElse(null));
        ticket.setPerson(personRepository.findById(person_id).orElse(null));

        return ticketRepository.save(ticket);
    }

    public Ticket findById(int id){
        return ticketRepository.findById(id).stream().findAny().orElse(null);
    }

    public void update(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void delete(int id) {
        ticketRepository.deleteById(id);
    }
}
