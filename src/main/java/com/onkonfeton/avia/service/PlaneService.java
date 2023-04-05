package com.onkonfeton.avia.service;

import com.onkonfeton.avia.model.Plane;
import com.onkonfeton.avia.repository.PlaneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneService {
    private final PlaneRepository planeRepository;

    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public List<Plane> findAll(){
        return planeRepository.findAll();
    }

    public Plane save(Plane person) {
        return planeRepository.save(person);
    }

    public Plane findById(int id){
        return planeRepository.findById(id).stream().findAny().orElse(null);
    }

    public void update(Plane person){
        planeRepository.save(person);
    }

    public void delete(int id) {
        planeRepository.deleteById(id);
    }
}
