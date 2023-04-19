package com.onkonfeton.avia.repository;

import com.onkonfeton.avia.model.City;
import com.onkonfeton.avia.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByDepartureCityAndArrivalCity(City departureCity, City arrivalCity);
}
