package com.schoolevents.schoolevents_api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "message")
class Message {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "content", length = 400, nullable = false)
    private String message;

    @Getter
    @Setter
    @Column(name = "send_date", nullable = false)
    private LocalDate send_date;

    @Getter
    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    public Message(String message, LocalDate send_date, User user) {
        this.message = message;
        this.send_date = send_date;
        this.user = user;
    }

    public Message() {
        this.send_date = LocalDate.now();
        this.user = new User();
        this.message = "Unknow";
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + getId() +
                ", message='" + getMessage() + '\'' +
                ", send_date=" + getSend_date() +
                ", user=" + getUser() +
                '}';
    }
}
