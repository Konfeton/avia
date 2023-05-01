package com.onkonfeton.avia.service;

import com.onkonfeton.avia.model.Airline;
import com.onkonfeton.avia.repository.AirlineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService {
    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public List<Airline> findAll(){
        return airlineRepository.findAll();
    }

    public Airline save(Airline airline) {
        return airlineRepository.save(airline);
    }

    public Airline findById(int id){
        return airlineRepository.findById(id).stream().findAny().orElse(null);
    }

    public void update(Airline airline){
        airlineRepository.save(airline);
    }

    public void delete(int id) {
        airlineRepository.deleteById(id);
    }

    public List<Airline> findByName(String name) {
        return airlineRepository.findByNameIgnoreCaseContaining(name);
    }
}
