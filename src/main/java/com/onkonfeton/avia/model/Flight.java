package com.onkonfeton.avia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime departureTime;
    @Column(name = "arrival_time", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime arrivalTime;
    @Column(name = "flight_info")
    private String flightInfo;
    private int price;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> tickets = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @OneToOne
    @JoinColumn(name = "departure_city_id", nullable = false)
    private City departureCity;

    @OneToOne
    @JoinColumn(name = "arrival_city_id", nullable = false)
    private City arrivalCity;

    public long flightTime(){
        return Duration.between(departureTime, arrivalTime).toHours();
    }

}
