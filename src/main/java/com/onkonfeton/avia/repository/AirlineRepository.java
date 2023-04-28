package com.onkonfeton.avia.repository;

import com.onkonfeton.avia.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline,Integer> {
}
