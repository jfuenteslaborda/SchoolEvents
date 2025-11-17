package com.schoolevents.schoolevents_api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Event")
class Event {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", length = 400, nullable = false)
    private String description;

    @Column(name = "price", length = 40, nullable = false)
    private Float price;

    @Column(name = "capacity", length = 200, nullable = false)
    private Integer capacity;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "need_payment")
    private Boolean need_payment;

    @OneToMany(mappedBy = "comment")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "image")
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "sign", cascade = CascadeType.ALL)
    private Set<Sign> signs = new HashSet<>();

}
