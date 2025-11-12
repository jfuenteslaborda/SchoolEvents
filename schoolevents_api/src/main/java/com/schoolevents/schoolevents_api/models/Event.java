package com.schoolevents.schoolevents_api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @Getter
    @OneToMany(mappedBy = "comment")
    private Set<Comment> comments = new HashSet<>();

    @Getter
    @OneToMany(mappedBy = "image")
    private Set<Image> images = new HashSet<>();

    @Getter
    @OneToMany(mappedBy = "sign", cascade = CascadeType.ALL)
    private Set<Sign> signs = new HashSet<>();

    public Event(String title, String description, Float price, Integer capacity, LocalDate date, Boolean need_payment, Set<Comment> comments, Set<Image> images, Set<Sign> signs) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.date = date;
        this.need_payment = need_payment;
        this.comments = comments;
        this.images = images;
        this.signs = signs;
    }

    public Event() {
        this.date = LocalDate.now();
        this.need_payment = false;
        this.comments = new HashSet<>();
        this.images = new HashSet<>();
        this.signs = new HashSet<>();
        this.title = "Unknow";
        this.description = "Unknow";
        this.price = 0.0f;
        this.capacity = 0;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", capacity=" + getCapacity() +
                ", date=" + getDate() +
                ", need_payment=" + getNeed_payment() +
                ", comments=" + getComments() +
                ", images=" + getImages() +
                ", signs=" + getSigns() +
                '}';
    }
}
