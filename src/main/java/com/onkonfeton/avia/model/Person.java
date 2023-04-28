package com.onkonfeton.avia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onkonfeton.avia.model.enums.Gender;
import com.onkonfeton.avia.model.enums.Role;
import com.onkonfeton.avia.model.enums.Status;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;
    @Column(name= "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "date_of_birthday")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirthday;
    private double miles;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonIgnore
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> tickets = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "card_id")
    private Card card;

    public String getFullName(){
        return lastName + " " + firstName;
    }

    public int years(){
            return Period.between(dateOfBirthday, LocalDate.now()).getYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.miles, miles) == 0 && Objects.equals(id, person.id) && Objects.equals(email, person.email) && Objects.equals(password, person.password) && Objects.equals(lastName, person.lastName) && Objects.equals(firstName, person.firstName) && Objects.equals(dateOfBirthday, person.dateOfBirthday) && role == person.role && status == person.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, lastName, firstName, dateOfBirthday, miles, role, status);
    }
}
