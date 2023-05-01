package com.onkonfeton.avia.repository;

import com.onkonfeton.avia.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepository extends JpaRepository<Airline,Integer> {
    List<Airline> findByNameIgnoreCaseContaining(String name);
}
