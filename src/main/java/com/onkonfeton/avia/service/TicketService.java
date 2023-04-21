package com.onkonfeton.avia.service;

import com.onkonfeton.avia.model.Person;
import com.onkonfeton.avia.model.Ticket;
import com.onkonfeton.avia.repository.FlightRepository;
import com.onkonfeton.avia.repository.PersonRepository;
import com.onkonfeton.avia.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        ticket.setTime(LocalDateTime.now());
        ticket.setFlight(flightRepository.findById(flight_id).orElse(null));
        ticket.setPerson(personRepository.findById(person_id).orElse(null));

        return ticketRepository.save(ticket);
    }

    public void update(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public void delete(int id) {
        ticketRepository.deleteById(id);
    }

    public List<Ticket> findByPerson(Person person){
        return ticketRepository.findByPerson(person);
    }
}
