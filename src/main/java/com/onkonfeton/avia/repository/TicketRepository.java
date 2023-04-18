package com.onkonfeton.avia.repository;

import com.onkonfeton.avia.model.Person;
import com.onkonfeton.avia.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByPerson(Person person);
}
