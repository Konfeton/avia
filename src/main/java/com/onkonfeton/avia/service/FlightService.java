package com.onkonfeton.avia.service;

import com.onkonfeton.avia.model.Flight;
import com.onkonfeton.avia.repository.FlightRepository;
import org.springframework.stereotype.Service;

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
}
