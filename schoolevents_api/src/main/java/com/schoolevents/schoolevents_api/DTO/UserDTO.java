package com.schoolevents.schoolevents_api.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schoolevents.schoolevents_api.models.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDTO {
    @NotBlank(message = "El id no debe de ser nulo")
    private Long id;
    @NotBlank(message = "El nombre no puede estar en blanco o nulo")
    private String full_name;
    @NotBlank(message = "Debe haber algun email")
    @Email
    private String email;
    private String password;
    private String photo;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Past(message = "La fecha no puede ser en el pasado")
    @NotBlank(message = "Debe de haber alguna fecha")
    private LocalDate date;
    @NotBlank(message = "Debe de tener algun rol")
    private Integer is_Admin;
}
