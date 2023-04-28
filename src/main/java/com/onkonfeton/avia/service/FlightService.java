package com.onkonfeton.avia.service;

import com.onkonfeton.avia.model.City;
import com.onkonfeton.avia.model.Flight;
import com.onkonfeton.avia.repository.FlightRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight save(Flight flight){
        return flightRepository.save(flight);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public void update(Flight flight) {
        flightRepository.save(flight);
    }

    public Flight findById(int id) {
        return  flightRepository.findById(id).stream().findAny().orElse(null);
    }


    public List<Flight> findByCitiesAndBetweenPrice(City departureCity, City arrivalCity, int price, int price2, Sort sort){
        return flightRepository.findByDepartureCityAndArrivalCityAndPriceBetween(departureCity, arrivalCity, price, price2, sort);
    }

    public List<Flight> findByDepartureTime(LocalDateTime departureTime){
        return flightRepository.findByDepartureTimeGreaterThan(departureTime);
    }


    public void delete(int id) {
        flightRepository.deleteById(id);
    }
}
