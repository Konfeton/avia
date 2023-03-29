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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;
    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;
    @Column(name = "flight_info" )
    private String flightInfo;
    private double price;

    @OneToOne
    @JoinColumn(name = "plane_id", nullable = false)
    private Plane plane;

    @OneToOne
    @JoinColumn(name = "departure_city_id", nullable = false)
    private City departureCity;

    @OneToOne
    @JoinColumn(name = "arrival_city_id", nullable = false)
    private City arrivalCity;


}
