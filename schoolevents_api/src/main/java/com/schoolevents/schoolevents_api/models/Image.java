package com.schoolevents.schoolevents_api.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "image")
class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "src", length = 150, nullable = false)
    private String src;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @ManyToOne @JoinColumn(name = "event_id")
    private Event event;

}
