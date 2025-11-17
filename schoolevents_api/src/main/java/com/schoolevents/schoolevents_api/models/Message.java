package com.schoolevents.schoolevents_api.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "message")
class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", length = 400, nullable = false)
    private String message;

    @Column(name = "send_date", nullable = false)
    private LocalDate send_date;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;
}
