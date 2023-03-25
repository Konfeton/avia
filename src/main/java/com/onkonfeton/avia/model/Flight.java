package com.onkonfeton.avia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;
    @Column(name = "flight_info")
    private String flightInfo;
    @Column(name = "number_of_seats")
    private int numberOfSeats;
    private double price;

    @OneToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;


    @OneToOne
    @JoinColumn(name = "departure_city_id")
    private City departureCity;

    @OneToOne
    @JoinColumn(name = "arrival_city_id")
    private City arrivalCity;


}
