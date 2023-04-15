package com.onkonfeton.avia.service;

import com.onkonfeton.avia.model.Person;
import com.onkonfeton.avia.model.enums.Role;
import com.onkonfeton.avia.model.enums.Status;
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
        if (person.getRole() == null) {
            person.setRole(Role.USER);
        }
        if (person.getStatus() == null) {
            person.setStatus(Status.ACTIVE);
        }
        return personRepository.save(person);
    }

    public Person findById(int id){
        return personRepository.findById(id).stream().findAny().orElse(null);
    }

    public void update(Person person){
        personRepository.save(person);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }
}
