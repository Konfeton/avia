package com.onkonfeton.avia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "date_of_expire")
    private Date dateOfExpire;
    @Column(name = "holder_name")
    private String holderName;
    private String cvv;

}