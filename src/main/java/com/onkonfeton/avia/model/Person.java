package com.onkonfeton.avia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onkonfeton.avia.model.enums.Role;
import com.onkonfeton.avia.model.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
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
    private LocalDate dateOfBirthday;
    private double miles;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonIgnore
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;

}
