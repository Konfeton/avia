package com.onkonfeton.avia.repository;

import com.onkonfeton.avia.model.City;
import com.onkonfeton.avia.model.Flight;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByDepartureCityAndArrivalCityAndPriceBetween(City departureCity, City arrivalCity, double price, double price2, Sort sort);
    List<Flight> findByDepartureTimeGreaterThan(LocalDateTime departureTime);

}
