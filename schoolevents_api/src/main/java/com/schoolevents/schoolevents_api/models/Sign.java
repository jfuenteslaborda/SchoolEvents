package com.schoolevents.schoolevents_api.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime; // Cambiado a LocalDateTime para incluir tiempo

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "sign")
public class Sign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("eventId")
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
}