package com.onkonfeton.avia.service;

import com.onkonfeton.avia.exceptions.PersonAlreadyExistException;
import com.onkonfeton.avia.model.Person;
import com.onkonfeton.avia.model.enums.Role;
import com.onkonfeton.avia.model.enums.Status;
import com.onkonfeton.avia.repository.PersonRepository;
import com.onkonfeton.avia.security.Hash;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Person save(Person person) throws PersonAlreadyExistException {
        if (findByEmail(person.getEmail()) != null){
            throw new PersonAlreadyExistException();
        }
        if (person.getRole() == null) {
            person.setRole(Role.USER);
        }
        if (person.getStatus() == null) {
            person.setStatus(Status.ACTIVE);
        }

        person.setPassword(Hash.encrypt(person.getPassword()));

        return personRepository.save(person);
    }

    public Person findByEmail(String email){
        return personRepository.findByEmail(email);
    }

    public Person findById(int id){
        return personRepository.findById(id).stream().findAny().orElse(null);
    }

    public void update(Person person, int id) throws PersonAlreadyExistException{
        Person byEmail = findByEmail(person.getEmail());
        if (byEmail != null && !byEmail.getId().equals(person.getId())){
            throw new PersonAlreadyExistException();
        }else {
            if (!person.getPassword().equals(findById(id).getPassword()))
                person.setPassword(Hash.encrypt(person.getPassword()));
            if (person.getFirstName() == null && person.getLastName() == null){
                person.setFirstName(findById(id).getFirstName());
                person.setLastName(findById(id).getLastName());
            }
            personRepository.save(person);
        }
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public List<Person> findByDateOfBirthdayBetween(LocalDate dateOfBirthday, LocalDate dateOfBirthday2) {
        return personRepository.findByDateOfBirthdayBetween(dateOfBirthday, dateOfBirthday2);
    }

    public List<Person> findByFirstName(String name) {
        return personRepository.findByFirstNameContaining(name);
    }

    public List<Person> findByLastName(String name) {
        return personRepository.findByLastNameContaining(name);
    }
}
