package com.schoolevents.schoolevents_api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

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

    @OneToMany(mappedBy = "app_user")
    private Set<Comment> comments = new HashSet<>();

}
