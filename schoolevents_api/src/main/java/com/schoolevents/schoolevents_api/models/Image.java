package com.schoolevents.schoolevents_api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "image")
class Image {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "src", length = 150, nullable = false)
    private String src;

    @Getter
    @Setter
    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Getter
    @ManyToOne @JoinColumn(name = "event_id")
    private Event event;

    public Image(String src, String description, Event event) {
        this.src = src;
        this.description = description;
        this.event = event;
    }

    public Image() {
        this.src = "Unknow";
        this.description = "Unknow";
        this.event = new Event();
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + getId() +
                ", src='" + getSrc() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", event=" + getEvent() +
                '}';
    }
}
