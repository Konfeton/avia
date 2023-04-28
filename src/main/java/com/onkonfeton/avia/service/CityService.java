package com.onkonfeton.avia.service;

import com.onkonfeton.avia.model.City;
import com.onkonfeton.avia.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAll(){
        return cityRepository.findAll();
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public City findById(int id){
        return cityRepository.findById(id).stream().findAny().orElse(null);
    }

    public void update(City city){
        cityRepository.save(city);
    }

    public void delete(int id) {
        cityRepository.deleteById(id);
    }
}
