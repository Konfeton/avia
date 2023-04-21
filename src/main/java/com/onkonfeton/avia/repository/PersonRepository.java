package com.onkonfeton.avia.repository;

import com.onkonfeton.avia.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByEmail(String email);
    List<Person> findByDateOfBirthdayBetween(LocalDate dateOfBirthday, LocalDate dateOfBirthday2);
}
