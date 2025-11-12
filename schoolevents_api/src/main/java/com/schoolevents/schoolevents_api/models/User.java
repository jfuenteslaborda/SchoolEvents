package com.schoolevents.schoolevents_api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "full_name", length = 50, nullable = false)
    private String full_name;

    @Getter
    @Setter
    @Column(name = "email", length = 60, unique = true, nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(name = "photo", length = 100)
    private String photo;

    @Getter
    @Setter
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Getter
    @Setter
    @Column(name = "is_admin")
    private Boolean is_Admin;

    @Getter
    @OneToMany(mappedBy = "app_user")
    private Set<Comment> comments = new HashSet<>();

    @Getter
    @OneToMany(mappedBy = "message")
    private Set<Message> messages = new HashSet<>();

    @Getter
    @OneToMany(mappedBy = "sign", cascade = CascadeType.ALL)
    private Set<Sign> signs = new HashSet<>();

    public User(String full_name, String email, String password, String photo, LocalDate date, Boolean is_Admin, Set<Comment> comments, Set<Message> messages, Set<Sign> signs) {
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.date = date;
        this.is_Admin = is_Admin;
        this.comments = comments;
        this.messages = messages;
        this.signs = signs;
    }

    public User() {
        this.date = LocalDate.now();
        this.is_Admin = false;
        this.comments = new HashSet<>();
        this.messages = new HashSet<>();
        this.signs = new HashSet<>();
        this.full_name = "Unknow";
        this.email = "Unknow";
        this.password = "";
        this.photo = "Unknow";
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", full_name='" + getFull_name() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", photo='" + getPhoto() + '\'' +
                ", date=" + getDate() +
                ", is_Admin=" + getIs_Admin() +
                ", comments=" + getComments() +
                ", messages=" + getMessages() +
                ", signs=" + getSigns() +
                '}';
    }
}
