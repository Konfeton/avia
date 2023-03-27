package com.onkonfeton.avia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onkonfeton.avia.model.enums.Role;
import com.onkonfeton.avia.model.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String login;
    private String password;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "date_of_birthday")
    private LocalDateTime dateOfBirthday;
    private String email;
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
