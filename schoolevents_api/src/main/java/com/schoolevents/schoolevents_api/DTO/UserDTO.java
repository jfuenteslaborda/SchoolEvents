package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.hibernate.sql.Update;

import java.time.LocalDate;

@Data
public class UserDTO {

    @NotNull(groups = Update.class, message = "El id de usuario no puede ser nulo")
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco o nulo")
    private String full_name;

    @NotBlank(message = "Debe haber algún email")
    @Email(message = "Debe ser un email válido")
    private String email;

    private String password;
    private String photo;

    @NotNull(message = "Debe de haber alguna fecha")
    @Past(message = "La fecha no puede ser en el futuro")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotNull(message = "Debe de tener algún rol")
    private Integer is_Admin;
}
