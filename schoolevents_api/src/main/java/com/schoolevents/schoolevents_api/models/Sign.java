package com.schoolevents.schoolevents_api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime; // Cambiado a LocalDateTime para incluir tiempo

@Entity
@Table(name = "sign")
@Getter
@Setter
@AllArgsConstructor
public class Sign {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("eventId")
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Getter
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    public Sign(User user, Event event, LocalDateTime date) {
        this.user = user;
        this.event = event;
        this.date = date;
    }

    public Sign(){
        this.date = LocalDateTime.now();
        this.user = new User();
        this.event = new Event();
    }

    @Override
    public String toString() {
        return "Sign{" +
                "id=" + getId() +
                ", user=" + getUser() +
                ", event=" + getEvent() +
                ", date=" + getDate() +
                '}';
    }
}