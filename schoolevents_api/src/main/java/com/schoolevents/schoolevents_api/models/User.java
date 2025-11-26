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
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 50, nullable = false)
    private String full_name;

    @Column(name = "email", length = 60, unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "photo", length = 100)
    private String photo;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "is_admin")
    private Integer is_Admin;
}
