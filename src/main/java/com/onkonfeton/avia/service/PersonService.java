package com.onkonfeton.avia.service;

import com.onkonfeton.avia.model.Person;
import com.onkonfeton.avia.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person findById(Long id){
        return personRepository.findById(id).stream().findAny().orElse(null);
    }

    public void update(Person person){
        personRepository.save(person);

    }

    public void delete(long id) {
        personRepository.deleteById(id);
    }
}
