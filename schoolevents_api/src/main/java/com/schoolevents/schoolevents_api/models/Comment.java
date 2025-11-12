package com.schoolevents.schoolevents_api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "comment")
class Comment {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "description", length = 400, nullable = false)
    private String description;

    @Getter
    @Setter
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToOne @JoinColumn(name = "user_id")
    private User user;

    @OneToOne @JoinColumn(name = "event_id")
    private Event event;

}
