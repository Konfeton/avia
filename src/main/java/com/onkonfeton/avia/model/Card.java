package com.onkonfeton.avia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "card_number", unique = true, nullable = false, length = 16)
    private String cardNumber;
    @Column(name = "date_of_expire", nullable = false)
    private LocalDate dateOfExpire;
    @Column(name = "holder_name", nullable = false)
    private String holderName;
    @Column(name = "cvv", nullable = false, length = 3)
    private String cvv;

}