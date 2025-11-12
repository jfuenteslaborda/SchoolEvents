package com.schoolevents.schoolevents_api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Event")
class Event {

    @Getter
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Getter
    @Setter
    @Column(name = "description", length = 400, nullable = false)
    private String description;

    @Getter
    @Setter
    @Column(name = "price", length = 40, nullable = false)
    private Float price;

    @Getter
    @Setter
    @Column(name = "capacity", length = 200, nullable = false)
    private Integer capacity;

    @Getter
    @Setter
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Getter
    @Setter
    @Column(name = "need_payment")
    private Boolean need_payment;
    

}
